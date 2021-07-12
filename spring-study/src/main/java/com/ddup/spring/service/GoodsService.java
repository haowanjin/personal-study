package com.ddup.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    public GoodsService(/*OrderService orderService*/) {
//        this.orderService = orderService;
        System.out.println("GoodsService constructor....");
    }


    @Autowired
    public String setOrderService(OrderService orderService) {
        System.out.println(orderService.hello());
        return "GoodsService say()";
    }
}
