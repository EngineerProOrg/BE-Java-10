package com.engineerpro.example.springdata.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booking {
  private int id;
  private User user;
  private Ticket ticket;
  private Timestamp bookingTime;
}
