package com.ddup.cloud.user.impl;

import com.ddup.cloud.db.mapper.EsUserMapper;
import com.ddup.cloud.entity.EsUser;
import com.ddup.cloud.service.EsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/10/25 18:07
 */
@Service
public class EsUserServiceImpl implements EsUserService {
    @Autowired
    private EsUserMapper userMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String save() {
        for (int i = 0; i < 50; i++) {
            int random = (int) (Math.random() + 1);
            int age = random * 10 + i + 1;
            EsUser esUser = new EsUser(i + 1, "张三" + age, 50 % (i + 1) == 0 ? "男" : "女", age, "西安市丈八北路丈八沟街道" + age + "号");
            userMapper.insertEsUser(esUser);
        }
        return "success";
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<EsUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    @Async
    public String asyncTest() {
        System.out.println(Thread.currentThread().getName());
        return "asyncTest";
    }
}
