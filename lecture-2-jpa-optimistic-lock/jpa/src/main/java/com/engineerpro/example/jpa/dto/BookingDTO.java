package com.engineerpro.example.jpa.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDTO {
  private Long id;
  private Long userId;
  private Long ticketId;
  private LocalDateTime bookingTime;
}
