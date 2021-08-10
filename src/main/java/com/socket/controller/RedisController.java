package com.socket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by jg 2021/08/10
 */
@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("redis1")
    public List<Object> test() {
        return redisTemplate.opsForList().range("chatNumber1", 0, -1);
    }

    @GetMapping("redis2")
    public List<Object> test2() {
        return redisTemplate.opsForList().range("chatNumber2", 0, -1);
    }

}
