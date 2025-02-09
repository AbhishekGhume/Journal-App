package com.abhishek.journalAppDB.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("name", "abhi");

//        Object email = redisTemplate.opsForValue().get("email");
        Object name = redisTemplate.opsForValue().get("name");
        Object age = redisTemplate.opsForValue().get("age");

        int a = 0;
    }
}
