package com.ddup.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    GoodsService goodsService;

    @Autowired
    public OrderService(GoodsService goodsService) {
        this.goodsService = goodsService;
        System.out.println("OrderService constructor....");
    }

    public String hello() {
        System.out.println(goodsService);
        goodsService.insert();
        return "OrderService hello";
    }
}
