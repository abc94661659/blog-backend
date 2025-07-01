package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment comment);

    void deleteById(Integer id);

    @Select("select * from comment where article_id = #{articleId}")
    List<Comment> selectCommentsByArticleId(Integer articleId);

    Comment selectById(Integer id);

    void deleteCommentsByArticleId(Integer articleId);


    void deleteCommentsByUserId(Integer id);

    void deleteCommentsByParentId(Integer id);

    List<Comment> selectCommentsByParentId(Integer parentId);

    List<Comment> selectParentCommentsByArticleId(Integer articleId);

    List<Comment> searchComments(String keyword);
}
