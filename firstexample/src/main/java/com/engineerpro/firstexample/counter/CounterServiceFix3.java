package com.engineerpro.firstexample.counter;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

@Service()
public class CounterServiceFix3 {
  private int counter = 0;
  private final ReentrantLock lock = new ReentrantLock();

  public int increment() {
    lock.lock();
    try {
      counter++;
      return counter;
    } finally {
      lock.unlock();
    }
  }

  public int getCounter() {
    return counter;
  }

  public void resetCounter() {
    counter = 0;
  }
}
