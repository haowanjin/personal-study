package com.ddup.spring.test;

import com.ddup.spring.service.impl.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
//@EnableAsync
public class DITest {
    @Autowired
    private GoodsService goodsService;


    @Test
    public void test() {
        System.out.println(Thread.currentThread().getName());
        goodsService.asyncTest();
    }
}
