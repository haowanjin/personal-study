package com.ddup.redis.study;

import com.ddup.redis.study.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringRedisStudyApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
   @Autowired
    private RedisTemplate<String,Object> redisObjTemplate;

    @Test
    void contextLoads() {
        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        value.set("hello", "hello redis");
        System.out.println(value.get("hello"));
        redisTemplate.delete("hello");
    }

    @Test
    void testHash() {
        User user = new User("123", "张三", 23, "南京路人民政府办公室");
        Map<String, String> map1 = new HashMap<>();
        map1.put("id", "135");
        map1.put("name", "张三");
        map1.put("age", "28");
        map1.put("address", "南京路人力街道");

        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "456");
        map2.put("name", "李四");
        map2.put("age", "28");
        map2.put("address", "南京路人力街道");

        redisObjTemplate.opsForHash().put("userHash","135",map1);

        ValueOperations<String, Object> operations = redisObjTemplate.opsForValue();

        operations.set("user1", user);
        redisTemplate.delete("adg");
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("adg", "测试setNx");
        Boolean bBoolean = redisTemplate.opsForValue().setIfAbsent("adg", "测试setNx");

        User user1 = (User) operations.get("user1");

        System.out.printf("a = %s\t", aBoolean);
        System.out.printf("b = %s\t", bBoolean);

        System.out.println(user1);

    }

}
