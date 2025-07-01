package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer id;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
    private String replyContent;
    private LocalDateTime replyTime;
}
