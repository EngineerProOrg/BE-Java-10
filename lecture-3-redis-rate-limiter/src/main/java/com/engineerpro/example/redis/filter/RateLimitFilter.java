package com.engineerpro.example.redis.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.engineerpro.example.redis.service.RateLimiterService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitFilter extends OncePerRequestFilter {
  private static final String API_KEY_HEADER = "X-API-KEY";

  private final RateLimiterService rateLimiterService;

  public RateLimitFilter(RateLimiterService rateLimiterService) {
    this.rateLimiterService = rateLimiterService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String apiKey = request.getHeader(API_KEY_HEADER);

    if (apiKey == null || apiKey.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("API key is missing");
      return;
    }

    if (rateLimiterService.isRateLimited(apiKey)) {
      response.setStatus(429);
      response.getWriter().write("Too many requests, try again later.");
    } else {
      filterChain.doFilter(request, response); // Allow the request to proceed
    }
  }

}
