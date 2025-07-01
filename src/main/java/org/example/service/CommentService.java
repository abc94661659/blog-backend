package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.Comment;

import java.util.List;

public interface CommentService {
    void insertComment(Comment comment);

    void deleteComment(Integer id);

    List<Comment> selectCommentsByArticleId(Integer articleId);

    PageInfo<Comment> selectCommentsByArticleIdWithPage(Integer articleId, int pageNum, int pageSize);
}
