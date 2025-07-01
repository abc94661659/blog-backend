package org.example.service.impl;

import org.example.entity.Announcements;
import org.example.mapper.AnnouncementsMapper;
import org.example.service.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {
    @Autowired
    private AnnouncementsMapper announcementsMapper;

    @Override
    public List<Announcements> findAll() {
        return announcementsMapper.findAll();
    }

    @Override
    @Transactional
    public void create(Announcements announcements) {
        announcements.setCreateTime(LocalDateTime.now());
        announcements.setUpdateTime(LocalDateTime.now());
        announcementsMapper.insert(announcements);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        announcementsMapper.deleteById(id);
    }

    @Override
    public void update(Announcements announcements) {
        announcements.setUpdateTime(LocalDateTime.now());
        announcementsMapper.update(announcements);
    }

    @Override

    public Announcements selectById(Integer id) {
        return announcementsMapper.selectById(id);
    }
}
