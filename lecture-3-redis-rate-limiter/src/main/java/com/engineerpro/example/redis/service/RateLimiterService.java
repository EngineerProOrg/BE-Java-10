package com.engineerpro.example.redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RateLimiterService {
  private static final int REQUEST_LIMIT = 5; // Limit to 100 requests
  private static final long TIME_WINDOW = 5 * 60; // Time window of 5 minutes in seconds

  private final StringRedisTemplate redisTemplate;

  public RateLimiterService(StringRedisTemplate stringRedisTemplate) {
    this.redisTemplate = stringRedisTemplate;
  }

  public boolean isRateLimited(String identifier) {
    String redisKey = "rate_limit:" + identifier;

    // Check if key exists, if not, initialize it with a value of 1 and set
    // expiration for 5 minutes
    if (!redisTemplate.hasKey(redisKey)) {
      log.info(redisKey + " does not exist, initializing with value 1");
      redisTemplate.opsForValue().set(redisKey, "1", TIME_WINDOW, TimeUnit.SECONDS);
      return false; // Not rate-limited on first request
    }

    // Increment the counter for this API key
    Long currentCount = redisTemplate.opsForValue().increment(redisKey);
    log.info(redisKey + " exists with value " + currentCount);
    // If the request count exceeds the limit, return true (rate-limited)
    return currentCount != null && currentCount > REQUEST_LIMIT;
  }
}
