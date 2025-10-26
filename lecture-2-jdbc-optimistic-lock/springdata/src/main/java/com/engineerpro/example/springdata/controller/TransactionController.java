package com.engineerpro.example.springdata.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.springdata.model.Transaction;
import com.engineerpro.example.springdata.repository.BookingRepository;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private BookingRepository bookingRepository;

  public TransactionController(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  @GetMapping("/history")
  public List<Transaction> getTransactionHistory(@RequestParam int userId) {
    return bookingRepository.getTransactionsByUserId(userId);
  }
}
