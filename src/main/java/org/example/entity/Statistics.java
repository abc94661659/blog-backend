package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private Integer id;
    private Integer totalArticles;
    private Integer totalUsers;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
