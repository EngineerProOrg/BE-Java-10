package com.engineerpro.example.springdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.springdata.service.TicketBookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private TicketBookingService bookingService;

    @PostMapping("/book-ticket")
    public ResponseEntity<String> bookTicket(@RequestParam int userId, @RequestParam int ticketId) {
        String result = bookingService.bookTicket(userId, ticketId);
        if (result.equals("Ticket booked successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}

