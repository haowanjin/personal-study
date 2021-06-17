package com.ddup.redis;

import redis.clients.jedis.*;

import java.util.LinkedList;

public class MyRedisClient {
    private static JedisPool pool;

    public static void main(String[] args) {
        int a = 12_33;
        System.out.println(a);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        pool = new JedisPool(config, "192.168.2.19", 6379, 3000, "yinfeng2012",8);
        Jedis jedis = pool.getResource();

        jedis.set("hello", "world");
        jedis.close();
    }

}
