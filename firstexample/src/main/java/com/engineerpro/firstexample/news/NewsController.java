package com.engineerpro.firstexample.news;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

  private final NewsService newsService;

  public NewsController(NewsService newsService) {
    this.newsService = newsService;
  }

  @GetMapping(value = "/top", produces = "application/json")
  public ResponseEntity<String> getWeather() {
    return ResponseEntity.status(HttpStatus.OK).body(newsService.getTopHeadlines());
  }
}
