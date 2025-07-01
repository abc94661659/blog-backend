package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);

    User selectByUsernameAndPassword(User u);

    User selectById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    void updatePassword(User u);

    User selectByUsernameOrEmail(User user);

    List<User> selectAllUsers();

    boolean selectByEmail(String email);

    User selectAdmin();

    @Select("select * from user")
    List<User> findAll();

    User selectByUsername(String username);


}
