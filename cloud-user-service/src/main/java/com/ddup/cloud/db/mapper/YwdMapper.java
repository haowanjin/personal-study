package com.ddup.cloud.db.mapper;

import com.ddup.user.entity.Ywd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface YwdMapper {

    @Select("select * from hoyi_ywd_ext where id=#{id} ")
    Ywd queryBydId(@Param("id") Long id);

    List<Ywd> queryYwdByParam(Ywd ywd);

    void saveYwd(Ywd ywd);
}
