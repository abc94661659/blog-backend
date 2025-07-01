package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    Article selectById(Integer id);

    Integer insertArticle(Article article);

    Article updateArticle(Article article);

    void deleteArticle(Integer id);

    PageInfo<Article> findByPage(int pageNum, int pageSize);

    Article selectByIdWithoutViewsUpdate(Integer id);

    List<Article> selectByCategoryId(Integer categoryId);

    List<Article> selectByAuthorId(Integer authorId);
}
