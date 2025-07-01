package org.example.service;

import org.example.entity.Announcements;
import org.example.entity.Article;

import java.util.List;

public interface SearchService {
    List<Article> searchArticles(String keyword);



    List<Announcements> searchAnnouncements(String keyword);
}
