package com.engineerpro.firstexample.counter;

import org.springframework.stereotype.Service;

@Service
public class CounterService {

  private int counter = 0;

  // Simulate a shared resource without synchronization
  public int increment() {
    counter++;
    return counter;
  }

  // Decrement the counter and return the new value
  // Note: like increment(), this is not synchronized and is for demo purposes only
  public int decrement() {
    counter--;
    return counter;
  }

  public int getCounter() {
    return counter;
  }

  public void resetCounter() {
    counter = 0;
  }
}
