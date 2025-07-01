package org.example.service;

import org.example.entity.User;
import org.example.entity.UserInfo;

import java.util.List;

public interface UserService {
    void save(User user);

    UserInfo login(User user);


    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    User getCurrentUser();



    boolean updatePasswordByUsernameOrEmail(User user);

    User checkUser(User user);

    boolean isEmailExists(String email);

    User getAdmin();

    List<User> findAll();

    boolean verifyUsernameAndEmail(String username, String email);
}
