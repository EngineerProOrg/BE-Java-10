package com.engineerpro.firstexample.counter;

import org.springframework.stereotype.Service;

@Service()
public class CounterServiceFixed {

  private int counter = 0;

  // Simulate a shared resource without synchronization
  public synchronized int increment() {
    counter++; // Now this operation is thread-safe
    return counter;
  }

  public int getCounter() {
    return counter;
  }

  public void resetCounter() {
    counter = 0;
  }
}
