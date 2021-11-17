package com.ddup.cloud.user.impl;


import com.alibaba.fastjson.JSON;
import com.ddup.cloud.db.mapper.YwdMapper;
import com.ddup.user.api.UserService;
import com.ddup.user.entity.User;
import com.ddup.user.entity.Ywd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private YwdMapper ywdMapper;

    static final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    static {
        userMap.put(1, new User(1, "wency", "女", 18, "丈八北路xxx"));
        userMap.put(2, new User(2, "lucy", "女", 21, "丈七东路xxx"));
        userMap.put(3, new User(3, "Jack", "男", 23, "丈六南路xxx"));
        userMap.put(4, new User(4, "chuMan", "男", 32, "丈九西路xxx"));
    }

    @Override
    public User getUserById(Integer id) {
//        log.debug("接收到请求，ThreadName={},", Thread.currentThread().getName());
        if (!userMap.containsKey(id)) {
            throw new RuntimeException("用户不存在，id=" + id);
        }


        if (id == 2) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
