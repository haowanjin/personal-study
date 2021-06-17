package com.ddup.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private GoodsService goodsService;

    public OrderService() {
        System.out.println("OrderService created");
    }
}
