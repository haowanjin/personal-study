package com.ddup.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {

    public GoodsService() {
        System.out.println("GoodsService constructor....");
    }

    @Transactional
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
