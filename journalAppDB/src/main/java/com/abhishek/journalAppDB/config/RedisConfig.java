package com.abhishek.journalAppDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // to make the serializer and deserializer of both (redis and spring boot project) same
    // initially RedisTemplet has default serializer and deserializer which does not same as redis serializer and deserializer
    // if we use as it is then key values we are setting in out spring boot projects are not visible in redis and vice versa

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) { // RedisConnectionFactory = helps in making connection with redis server
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //saving both key and value as string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
