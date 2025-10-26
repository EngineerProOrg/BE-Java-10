package com.engineerpro.example.springdata.model;

import org.springframework.data.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
  private int id;
  private String seat;
  private double price;
  private boolean isBooked;
  @Version
  private int version;
}
