package com.ddup.cloud.service;

import com.ddup.cloud.entity.EsUser;

import java.util.List;

public interface EsUserService {
    String save();

    List<EsUser> getAll();

    String asyncTest();
}
