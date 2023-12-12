package com.mgmetehan.rediscache.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void deleteCache(String key) {
        redisTemplate.delete(key);
        //DEL "usersCache::getUsers"
        log.info("Cache deleted with key: {}", key);
    }
}
