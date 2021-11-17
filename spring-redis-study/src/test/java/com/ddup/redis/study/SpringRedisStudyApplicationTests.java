package com.ddup.redis.study;

import com.ddup.redis.study.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

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
      /*  Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("name", "张三");
        map.put("age", "28");
        map.put("address", "南京路人力街道");
*/
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
