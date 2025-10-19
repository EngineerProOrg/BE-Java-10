package com.engineerpro.firstexample.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.url}")
  private String apiUrl;

  private final RestTemplate restTemplate;

  public WeatherService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getWeather(String city) {
    // Construct the URL with query parameters
    String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
        .queryParam("q", city)
        .queryParam("appid", apiKey)
        .queryParam("units", "metric") // Use metric units for temperature
        .toUriString();
    log.info("Fetching weather data from URL: {}", url);

    // Make the API call and return the result as a String
    return restTemplate.getForObject(url, String.class);
  }
}
