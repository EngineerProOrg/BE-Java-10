package com.engineerpro.example.springdata.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineerpro.example.springdata.model.Ticket;
import com.engineerpro.example.springdata.model.User;
import com.engineerpro.example.springdata.repository.BookingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketBookingService {
    private BookingRepository bookingRepository;

    public TicketBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public String bookTicket(int userId, int ticketId) {
        log.info("User " + userId + " book ticket " + ticketId);
        // Fetch ticket details, ensure it's not already booked
        Optional<Ticket> ticketOpt = bookingRepository.getTicketByIdForUpdate(ticketId);
        log.info("User " + userId + " query ticket, got result = " + ticketOpt.isPresent());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        if (!ticketOpt.isPresent()) {
            return "Ticket is already booked or does not exist.";
        }

        Ticket ticket = ticketOpt.get();

        // Fetch user details and ensure enough balance
        Optional<User> userOpt = bookingRepository.getUserByIdForUpdate(userId);
        if (!userOpt.isPresent()) {
            return "User does not exist.";
        }

        User user = userOpt.get();

        if (user.getBalance() < ticket.getPrice()) {
            return "Insufficient balance to book this ticket.";
        }

        // Deduct the price from user balance
        double newBalance = user.getBalance() - ticket.getPrice();
        bookingRepository.updateUserBalance(userId, newBalance);

        // Save transaction record (Debit)
        bookingRepository.saveTransaction(userId, ticket.getPrice(), "DEBIT");

        // Mark ticket as booked
        bookingRepository.markTicketAsBooked(ticketId);

        // Insert booking record
        bookingRepository.insertBooking(userId, ticketId);

        return "Ticket booked successfully!";
    }
}
