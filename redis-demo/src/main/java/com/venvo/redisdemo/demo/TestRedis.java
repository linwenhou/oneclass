package com.venvo.redisdemo.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author venvo
 * @date 2021-08-25 15:40
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
public class TestRedis {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void addString() {
//        redisTemplate.opsForValue().set("hello", "venvo");
//        System.out.println(redisTemplate.opsForValue().get("hello"));
//
//        stringRedisTemplate.opsForValue().set("lin", "wenhou");
//        System.out.println(stringRedisTemplate.opsForValue().get("lin"));

        Person person = new Person();
        person.setAge(19);
        person.setName("lisi");
        redisTemplate.opsForHash().put("sean","ll",person);


        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);

        stringRedisTemplate.opsForHash().putAll("sean01",jm.toHash(person));

        Map map = stringRedisTemplate.opsForHash().entries("sean01");

        Person per = objectMapper.convertValue(map, Person.class);
        System.out.println(per.getName());
    }
}
