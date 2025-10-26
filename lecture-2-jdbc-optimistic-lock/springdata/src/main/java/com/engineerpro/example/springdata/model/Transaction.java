package com.engineerpro.example.springdata.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Transaction {
  private int id;
  private int userId;
  private double amount;
  private String transactionType;
  private Timestamp transactionTime;
}
