package com.engineerpro.example.redis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.engineerpro.example.redis.filter.RateLimitFilter;
import com.engineerpro.example.redis.service.RateLimiterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class AppConfig {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public FilterRegistrationBean<RateLimitFilter> loggingFilter(RateLimiterService rateLimiterService) {
    FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new RateLimitFilter(rateLimiterService));
    registrationBean.setOrder(1);

    return registrationBean;
  }

}
