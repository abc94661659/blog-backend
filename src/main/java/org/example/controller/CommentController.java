package org.example.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Comment;
import org.example.entity.Result;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @param comment 评论对象
     * @return 操作结构
     */
    @PostMapping
    @Transactional
    public Result insertComment(@RequestBody Comment comment){
        log.info("新增评论：{}", comment);
        commentService.insertComment(comment);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return Result.success();
    }

    @GetMapping("/article/{articleId}")
    public Result selectCommentsByArticleId(@PathVariable Integer articleId){
        List<Comment> comments = commentService.selectCommentsByArticleId(articleId);
        log.info("查询文章id为{}的评论", comments);
        return Result.success(comments);
    }

    @GetMapping("/article/{articleId}/page")
    public Result selectCommentsByArticleIdWithPage(@PathVariable Integer articleId,
                                                    @RequestParam int pageNum,
                                                    @RequestParam int pageSize) {
        PageInfo<Comment> pageInfo = commentService.selectCommentsByArticleIdWithPage(articleId, pageNum, pageSize);
        log.info("分页查询文章id为{}的评论，第 {} 页，每页 {} 条", articleId, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
