package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Announcements;
import org.example.entity.Result;
import org.example.service.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@Slf4j
public class AnnouncementsController {
    @Autowired
    private AnnouncementsService announcementsService;

    @GetMapping
    public Result findAll() {
        List<Announcements> list = announcementsService.findAll();
        log.info("查询所有公告");
        return Result.success(list);
    }

    @PostMapping("/create")
    public Result create(@RequestBody Announcements announcements) {
        announcementsService.create(announcements);
        log.info("新增公告栏成功");
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Result delete(@PathVariable Integer id) {
        announcementsService.deleteById(id);
        log.info("id为{}的记录删除成功", id);
        return Result.success("id为" + id + "删除成功");
    }

    @PutMapping
    @Transactional
    public Result update(@RequestBody Announcements announcements) {
        announcementsService.update(announcements);
        log.info("id为{}的记录更新成功", announcements.getId());
        return Result.success("公告栏更新成功");
    }

    @GetMapping("/{id}")
    @Transactional
    public Result selectById(@PathVariable Integer id) {
        Announcements announcements = announcementsService.selectById(id);
        log.info("id为{}的记录查询成功", id);
        return Result.success(announcements);
    }
}
