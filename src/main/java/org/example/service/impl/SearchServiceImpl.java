package org.example.service.impl;

import org.example.entity.Announcements;
import org.example.entity.Article;
import org.example.mapper.AnnouncementsMapper;
import org.example.mapper.ArticleMapper;
import org.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AnnouncementsMapper announcementsMapper;

    @Override
    public List<Article> searchArticles(String keyword) {
        return articleMapper.searchArticles(keyword);
    }



    @Override
    public List<Announcements> searchAnnouncements(String keyword) {
        return announcementsMapper.searchAnnouncements(keyword);
    }
}
