package com.ddup.cloud.service;

import com.ddup.cloud.entity.EsUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsUserService {
    long count();

    EsUser save(EsUser EsUser);

    void delete(EsUser EsUser);

    Iterable<EsUser> getAll();

    List<EsUser> getByName(String name);

    Page<EsUser> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
