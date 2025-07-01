package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private String content;
    @JsonProperty("parent_comment_id") // 指定 JSON 字段名
    private Integer parentCommentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Comment> children; // 新增：用于存储子评论

    // 新增：用户相关字段（数据库无，需通过接口查询填充）
    private String userName;
    private String userAvatar;

}
