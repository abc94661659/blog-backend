package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public LocalDateTime createTime;
    public LocalDateTime updateTime;
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private String role;

}
