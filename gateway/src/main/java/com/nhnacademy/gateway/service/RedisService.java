package com.nhnacademy.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 토큰을 블랙리스트에 추가하는 메서드
    public void addTokenToBlacklist(String token, long expirationMillis) {
        redisTemplate.opsForValue().set(token, "logout", expirationMillis, TimeUnit.MILLISECONDS);
    }

    // 토큰이 블랙리스트에 있는지 확인하는 메서드
    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
