package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Result;
import org.example.entity.Statistics;
import org.example.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@Slf4j
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    // 合并更新和查询功能的接口
    @PostMapping("/updateAndGet")
    public Result updateAndGetStatistics() {

        // 创建一个空的 Statistics 对象用于更新
        Statistics statistics = new Statistics();
        // 调用更新方法
        statisticsService.updateStatistics(statistics);
        // 调用查询方法获取更新后的统计数据
        Statistics updatedStatistics = statisticsService.getStatistics();
        return Result.success(updatedStatistics);
    }
}
