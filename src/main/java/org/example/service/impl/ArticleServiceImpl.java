package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Article;
import org.example.exception.UserContextHolder;
import org.example.mapper.ArticleMapper;
import org.example.mapper.CommentMapper;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public Article selectById(Integer id) {
        articleMapper.updateViews(id);
        return articleMapper.selectById(id);
    }

    @Override
    public Integer insertArticle(Article article) {

        article.setAuthorId(UserContextHolder.getUserId());
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setViews(0);
        articleMapper.insert(article);

        return article.getId();
    }

    @Override
    public Article updateArticle(Article article) {

        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
        return article;
    }

    @Override
    public void deleteArticle(Integer id) {
        // 先删除文章的评论
        commentMapper.deleteCommentsByArticleId(id);
        // 在删除文章
        articleMapper.deleteById(id);

    }

    @Override
    public PageInfo<Article> findByPage(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询文章列表
        List<Article> articles = articleMapper.selectAll();
        // 封装分页信息
        return new PageInfo<>(articles);
    }

    @Override
    public Article selectByIdWithoutViewsUpdate(Integer id) {
        // 查询文章但不增加浏览量

        return articleMapper.selectById(id);
    }

    @Override
    public List<Article> selectByCategoryId(Integer categoryId) {
        return articleMapper.selectByCategoryId(categoryId);
    }

    @Override
    public List<Article> selectByAuthorId(Integer authorId) {
        return articleMapper.selectByAuthorId(authorId);
    }
}
