package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> selectAll();

    Article selectById(Integer id);

    void insert(Article article);

    void update(Article article);

    void deleteById(Integer id);

    void updateViews(Integer id);


    List<Article> selectByCategoryId(Integer categoryId);

    List<Integer> selectArticleIdsByAuthorId(Integer id);

    List<Article> selectByAuthorId(Integer authorId);

    List<Article> searchArticles(String keyword);
}
