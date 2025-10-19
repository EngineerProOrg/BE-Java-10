package com.engineerpro.firstexample.counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/counter")
public class CounterController {

  private final CounterService counterService;

  public CounterController(CounterService counterService) {
    this.counterService = counterService;
  }

  @GetMapping("/reset")
  public String resetCounter() {
    counterService.resetCounter();
    return "Counter value: " + this.getCounter();
  }

  @GetMapping("/increment")
  public String incrementCounter() {
    int newCounterValue = counterService.increment();
    log.info("newCounterValue: " + newCounterValue);
    return "Counter value: " + newCounterValue;
  }

  @GetMapping("/value")
  public String getCounter() {
    return "Current counter value: " + counterService.getCounter();
  }
}
