package com.engineerpro.firstexample.counter;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service()
public class CounterServiceFix2 {

  AtomicInteger counter = new AtomicInteger(0);

  public int increment() {
    return counter.incrementAndGet(); // Atomic operation
  }

  public int getCounter() {
    return counter.get();
  }

  public void resetCounter() {
    counter = new AtomicInteger(0);
  }
}
