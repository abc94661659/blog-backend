package org.example.service;

import org.example.entity.Announcements;

import java.util.List;

public interface AnnouncementsService {
    List<Announcements> findAll();

    void create(Announcements announcements);

    void deleteById(Integer id);

    void update(Announcements announcements);

    Announcements selectById(Integer id);
}
