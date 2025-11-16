package com.engineerpro.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DistributedLockService {

    private static final String LOCK_PREFIX = "lock:";
    private static final long EXPIRATION_TIME = 5; // 5 minutes

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean acquireLock(String lockKey) {
        String fullLockKey = LOCK_PREFIX + lockKey;
        Boolean success = redisTemplate.opsForValue().setIfAbsent(fullLockKey, "locked", EXPIRATION_TIME,
                TimeUnit.MINUTES);
        return success != null && success;
    }

    public void releaseLock(String lockKey) {
        String fullLockKey = LOCK_PREFIX + lockKey;
        redisTemplate.delete(fullLockKey);
    }
}
