package com.ddup.spring.test;

import com.ddup.spring.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CircleDependency {
   @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        orderService.hello();
    }
}
