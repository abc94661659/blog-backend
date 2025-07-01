package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.entity.UserInfo;
import org.example.exception.UserContextHolder;
import org.example.mapper.ArticleMapper;
import org.example.mapper.CommentMapper;
import org.example.mapper.MessageMapper;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.utils.BCryptUtils;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Qualifier("messageMapper")
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void save(User user) {
        user.setPassword(BCryptUtils.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setAvatar("https://img.51miz.com/Element/00/88/63/61/7d962719_E886361_53733d01.png");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);

    }

    @Override
    public UserInfo login(User user) {
        // 先根据用户名查询用户
        User u = userMapper.selectByUsername(user.getUsername());
        log.info(user.getPassword());
        if (u != null) {
            // 直接使用 BCrypt 验证
            if (BCryptUtils.matches(user.getPassword(), u.getPassword())) {
                return generateUserInfo(u);
            }
        }

        return null;
    }

    private UserInfo generateUserInfo(User u) {
        String id = Integer.toString(u.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", u.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        return new UserInfo(Integer.valueOf(id), u.getUsername(), jwt, u.getRole());
    }

    @Override
    public User getById(Integer id) {

        User user = userMapper.selectById(id);
        user.setPassword("******");
        return user;
    }

    @Override
    public void update(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(BCryptUtils.encode(user.getPassword()));
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        // 删除用户发布的文章
        List<Integer> articleIds = articleMapper.selectArticleIdsByAuthorId(id);
        for (Integer articleId : articleIds) {
            //删除文章关联的评论
            commentMapper.deleteCommentsByArticleId(articleId);
            // 删除文章
            articleMapper.deleteById(articleId);

        }
        commentMapper.deleteCommentsByUserId(id);
        messageMapper.deleteByUserId(id);
        // 删除用户
        userMapper.deleteById(id);


    }

    @Override
    public User getCurrentUser() {
        return userMapper.selectById(UserContextHolder.getUserId());
    }


    @Override
    public User checkUser(User user) {
        User u = userMapper.selectByUsernameOrEmail(user);
        return u;
    }

    @Override
    public boolean updatePasswordByUsernameOrEmail(User user) {
        user.setPassword(BCryptUtils.encode(user.getPassword()));
        userMapper.updatePassword(user);
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {

        return userMapper.selectByEmail(email);
    }

    @Override
    public User getAdmin() {
        return userMapper.selectAdmin();
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }


    @Override
    public boolean verifyUsernameAndEmail(String username, String email) {
        User user = userMapper.selectByUsername(username);
        return user != null && user.getEmail().equals(email);
    }
}
