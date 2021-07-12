package com.ddup.spring.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public OrderService(/*GoodsService goodsService*/) {
       // this.goodsService = goodsService;
        System.out.println("OrderService constructor....");
    }

    public String hello() {
        return "OrderService hello";
    }
}
