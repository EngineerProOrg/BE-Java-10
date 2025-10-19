package com.engineerpro.firstexample.weather;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  private final WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/{city}")
  public ResponseEntity<String> getWeather(@PathVariable String city) {
    return ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeather(city));
  }
}
