package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Statistics;

@Mapper
public interface StatisticsMapper {

    void updateStatistics(Statistics statistics);

    Statistics selectStatistics();

    @Select("SELECT COUNT(*) FROM user")
    Integer selectTotalUsers();

    @Select("SELECT COUNT(*) FROM article")
    Integer selectTotalArticles();
}
