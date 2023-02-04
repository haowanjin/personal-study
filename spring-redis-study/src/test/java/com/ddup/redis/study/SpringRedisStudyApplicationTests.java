package com.ddup.redis.study;

import com.ddup.redis.study.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest
class SpringRedisStudyApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisObjTemplate;
    @Autowired
    private RedisClusterConnection redisConnection;


    @Test
    public void testCluster() {
        ClusterOperations<String, Object> cluster = redisObjTemplate.opsForCluster();
//        cluster.shutdown();
        Set<String> keys = cluster.keys(RedisClusterNode.newRedisClusterNode().withId("aa5234701bba5dfa703061b69fe5e8fd8cde4cfb").build(), "*");
        System.out.println(keys);
    }

    @Test
    public void testConn() {
        Set<byte[]> set = redisConnection.keys("*".getBytes(StandardCharsets.UTF_8));
        assert set != null;
        List<String> collect = set.stream().map(String::new).collect(Collectors.toList());
        System.out.println(collect);
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

        redisObjTemplate.opsForValue().set("abc", "cluster test");

        redisObjTemplate.opsForHash().put("userCache", "135", map1);
        redisObjTemplate.opsForHash().put("userCache", "123", user);
        redisObjTemplate.opsForHash().put("userCache", "456", map2);

        ValueOperations<String, Object> operations = redisObjTemplate.opsForValue();

        operations.set("user1", user);

        User user1 = (User) operations.get("user1");
        Object userCache = redisObjTemplate.opsForHash().get("userCache", user.getId());
        Object rMap1 = redisObjTemplate.opsForHash().get("userCache", map1.get("id"));
        System.out.println(userCache);
        System.out.println(rMap1);
        System.out.println(user1);
    }

    @Test
    public void testList() {
        redisObjTemplate.delete("ListTest");
        redisObjTemplate.opsForList().leftPushAll("ListTest", 1, 2, 5, 3, 4);
        redisObjTemplate.opsForList().rightPushAll("ListTest", 22, 33, 55, 11);
        redisObjTemplate.expire("ListTest", 10, TimeUnit.SECONDS);
        System.out.println(redisObjTemplate.opsForList()
                .leftPop("ListTest"));
    }

    @Test
    public void testSet() {
        String key = "SetTest";
        redisObjTemplate.opsForSet().add(key, 22, 1, 44, 20, 11, 12, 55, 02, 43);
        System.out.println(redisObjTemplate.opsForSet().members(key));
    }

}
