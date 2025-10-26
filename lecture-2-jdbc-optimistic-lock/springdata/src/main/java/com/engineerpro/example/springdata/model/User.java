package com.engineerpro.example.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private int id;
  private String username;
  private double balance;

  public User(String username, double balance) {
    this.username = username;
    this.balance = balance;
  }
}
