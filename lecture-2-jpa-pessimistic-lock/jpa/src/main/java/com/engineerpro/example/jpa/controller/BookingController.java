package com.engineerpro.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.jpa.dto.BookingDTO;
import com.engineerpro.example.jpa.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private BookingService bookingService;

  @Autowired
  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping("/book-ticket")
  public ResponseEntity<BookingDTO> bookTicket(@RequestParam Long userId, @RequestParam Long ticketId) {
    BookingDTO bookingDTO = bookingService.bookTicket(userId, ticketId);
    return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
  }
}
