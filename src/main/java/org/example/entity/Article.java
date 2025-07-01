package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer authorId;
    private String categoryId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer views;
}
