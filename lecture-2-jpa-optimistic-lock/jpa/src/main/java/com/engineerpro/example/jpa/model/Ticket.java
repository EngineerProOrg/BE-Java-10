package com.engineerpro.example.jpa.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String seat;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private Boolean isBooked = false;

  @Version
  private Integer version;
}
