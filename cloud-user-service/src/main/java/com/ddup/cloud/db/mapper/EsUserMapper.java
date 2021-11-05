package com.ddup.cloud.db.mapper;

import com.ddup.cloud.entity.EsUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EsUserMapper {
    List<EsUser> getAll();

    int insertEsUser(EsUser esUser);
}
