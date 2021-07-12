package com.ddup.spring.test;

import com.ddup.spring.service.GoodsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.ddup.spring")
public class CircleDependency {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(CircleDependency.class);
      //  ac.refresh();

        GoodsService goodsService = ac.getBean("goodsService", GoodsService.class);
    }
}
