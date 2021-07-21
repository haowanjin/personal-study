package com.ddup.cloud.user;

import com.ddup.cloud.db.mapper.YwdMapper;
import com.ddup.user.entity.Ywd;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.ddup.cloud.db.mapper")
class CloudUserServiceApplicationTests {
    @Autowired
    private YwdMapper mapper;

    @Test
    void contextLoads() {
        Ywd ywd = new Ywd();
        ywd.setYwdName("%' or 1=1 -- ");
        List<Ywd> ywds = mapper.queryYwdByParam(ywd);
        System.out.println(ywds);
    }

}
