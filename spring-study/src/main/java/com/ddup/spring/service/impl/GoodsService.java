package com.ddup.spring.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("goodsService1")
@Qualifier("goodsService1")
public class GoodsService {

    public GoodsService() {
        System.out.println("impl GoodsService constructor....");
    }

//    @Transactional
    public String getUser() {
        GoodsService gs1 = this;
        System.out.println(gs1);
        return "hello everyone";
    }

    public void insert() {
        System.out.println(this);
        getUser();
    }

}
