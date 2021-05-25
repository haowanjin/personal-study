package com.ddup.cloud.user.impl;


import com.ddup.user.api.UserService;
import com.ddup.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    static final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    static {
        userMap.put(1, new User(1, "wency", "女", 18, "丈八北路xxx"));
        userMap.put(2, new User(2, "lucy", "女", 21, "丈七东路xxx"));
        userMap.put(3, new User(3, "Jack", "男", 23, "丈六南路xxx"));
        userMap.put(4, new User(4, "chuMan", "男", 32, "丈九西路xxx"));
    }

    @Override
    public User getUserById(Integer id) {

        return userMap.get(id);
    }

    @Override
    public void saveUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<>(userMap.values());
    }
}
