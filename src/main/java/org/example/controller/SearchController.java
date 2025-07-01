package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Announcements;
import org.example.entity.Article;
import org.example.entity.Result;
import org.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping
    public Result globalSearch(@RequestParam String keyword){
        log.info("收到搜索请求，关键词: {}", keyword);
        List<Article> articles = searchService.searchArticles(keyword);
        List<Announcements> announcements = searchService.searchAnnouncements(keyword);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("articles", articles);
        resultMap.put("announcements", announcements);
        log.info("搜索成功");
        return Result.success(resultMap);
    }
}
