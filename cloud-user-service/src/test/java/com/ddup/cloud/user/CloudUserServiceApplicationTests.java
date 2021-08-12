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

    @Test
    public void testInsert() {
        Ywd ywd = new Ywd();
        ywd.setId(123L);
        ywd.setVersion(1);
        ywd.setCode("ABC123");
        ywd.setBankCode("TYPRCB");
        ywd.setYwdName("MyBatisTest");
        ywd.setUserCode("ABCUser");
        ywd.setYwdUserType("01");
        ywd.setInviteYwdCode("AGB456");
        ywd.setYwdLevel(1);
        ywd.setDevPath(".1.12.113.");
        ywd.setEmpCode("BF4321");
        ywd.setEmpNo("9999");
        ywd.setDeptCode("ALD");
        ywd.setTelephone("1818818183");
        ywd.setYwdStatus("01");
        ywd.setRemark("测试三");
        ywd.setDelFlag("0");
        mapper.saveYwd(ywd);
    }

}
