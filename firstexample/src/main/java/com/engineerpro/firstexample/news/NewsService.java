package com.engineerpro.firstexample.news;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsService {

  @Value("${news.api.key}")
  private String apiKey;

  @Value("${news.api.url}")
  private String apiUrl;

  private final RestTemplate restTemplate;

  public NewsService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getTopHeadlines() {
    // https://newsapi.org/docs/endpoints/top-headlines
    // Construct the URL with query parameters
    String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
        .queryParam("country", "us")
        .queryParam("apiKey", apiKey)
        .toUriString();

    // Make the API call and return the result as a String
    return restTemplate.getForObject(url, String.class);
  }
}
