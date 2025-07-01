package org.example.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Article;
import org.example.entity.Result;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result findAll() {
        List<Article> list = articleService.findAll();
        return Result.success(list);
    }

    @GetMapping("/detail/{id}")
    public Result selectById(@PathVariable Integer id) {
        // 查看详情时增加浏览量
        Article article =  articleService.selectById(id);
        return Result.success(article);
    }



    @GetMapping("/{id}")
    public Result selectByIdForEdit(@PathVariable Integer id) {
        // 根据文章ID查询用于编辑的文章信息，不更新浏览量
        Article article = articleService.selectByIdWithoutViewsUpdate(id);
        return Result.success(article);
    }

    @PostMapping("/create")
    @Transactional
    public Result insertArticle(@RequestBody Article article){
        Integer id=articleService.insertArticle(article);
        log.info("文章ID：{}",id);
        return Result.success(id);
    }

    @PutMapping("/{id}")
    public Result updateArticle(@PathVariable Integer id,@RequestBody Article article){
        article.setId(id);
        articleService.updateArticle(article);

        return Result.success(article);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteArticle(@PathVariable Integer id){
        articleService.deleteArticle(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {

        PageInfo<Article> pageInfo = articleService.findByPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/category/{categoryId}")
    public Result findArticleByCategoryId(@PathVariable Integer categoryId) {
        List<Article> article = articleService.selectByCategoryId(categoryId);

        return Result.success(article);
    }


    @GetMapping("/author/{authorId}")
    public Result selectByAuthorId(@PathVariable Integer authorId) {

        List<Article> articles = articleService.selectByAuthorId(authorId);
        return Result.success(articles);
    }

}
