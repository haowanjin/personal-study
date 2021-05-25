package com.ddup.user.api;

import com.ddup.user.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);

    void saveUser(User user);

    List<User> listUser();
}
