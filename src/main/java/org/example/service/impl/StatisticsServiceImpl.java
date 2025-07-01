package org.example.service.impl;

import org.example.entity.Statistics;
import org.example.mapper.StatisticsMapper;
import org.example.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public void updateStatistics(Statistics statistics) {
        statistics.setId(1);
        statistics.setTotalArticles(statisticsMapper.selectTotalArticles());
        statistics.setTotalUsers(statisticsMapper.selectTotalUsers());
        statisticsMapper.updateStatistics(statistics);
    }

    @Override
    public Statistics getStatistics() {

        return statisticsMapper.selectStatistics();
    }


}
