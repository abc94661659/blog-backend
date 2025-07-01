package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Comment;
import org.example.entity.User;
import org.example.mapper.CommentMapper;
import org.example.service.CommentService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Override
    public void insertComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentMapper.insertComment(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        // 递归删除所有子评论
        deleteChildComments(id);
        commentMapper.deleteById(id);
    }

    private void deleteChildComments(Integer parentId) {
        // 查询当前层级的子评论
        List<Comment> childComments = commentMapper.selectCommentsByParentId(parentId);
        for (Comment childComment : childComments) {
            // 递归删除子评论的子评论
            deleteChildComments(childComment.getId());
        }
        // 删除当前层级的子评论
        commentMapper.deleteCommentsByParentId(parentId);
    }
    @Override
    public List<Comment> selectCommentsByArticleId(Integer articleId) {
        return commentMapper.selectCommentsByArticleId(articleId);
    }

    @Override
    @Transactional
    public PageInfo<Comment> selectCommentsByArticleIdWithPage(Integer articleId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> parentComments = commentMapper.selectParentCommentsByArticleId(articleId);

        for (Comment parentComment : parentComments) {
            // 递归填充子评论
            fillChildComments(parentComment);
        }

        return new PageInfo<>(parentComments);
    }

    private void fillChildComments(Comment comment) {
        // 查询子评论
        List<Comment> childComments = commentMapper.selectCommentsByParentId(comment.getId());
        comment.setChildren(childComments);

        // 查询用户信息（假设存在UserService）
        User user = userService.getById(comment.getUserId());
        comment.setUserName(user.getUsername());
        comment.setUserAvatar(user.getAvatar());

        // 递归填充子评论的用户信息和子评论
        for (Comment childComment : childComments) {
            fillChildComments(childComment);
        }
    }
}
