package com.engineerpro.example.jpa.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineerpro.example.jpa.dto.BookingDTO;
import com.engineerpro.example.jpa.exception.CustomException;
import com.engineerpro.example.jpa.mapper.BookingMapper;
import com.engineerpro.example.jpa.model.Booking;
import com.engineerpro.example.jpa.model.Ticket;
import com.engineerpro.example.jpa.model.Transaction;
import com.engineerpro.example.jpa.model.TransactionType;
import com.engineerpro.example.jpa.model.User;
import com.engineerpro.example.jpa.repository.BookingRepository;
import com.engineerpro.example.jpa.repository.TicketRepository;
import com.engineerpro.example.jpa.repository.TransactionRepository;
import com.engineerpro.example.jpa.repository.UserRepository;

@Service
public class BookingService {
  private TicketRepository ticketRepository;
  private UserRepository userRepository;
  private BookingRepository bookingRepository;
  private TransactionRepository transactionRepository;

  public BookingService(TicketRepository ticketRepository, UserRepository userRepository,
      BookingRepository bookingRepository, TransactionRepository transactionRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  public BookingDTO bookTicket(Long userId, Long ticketId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND.value()));

    // Use pessimistic lock to prevent concurrent booking of the same ticket
    Ticket ticket = ticketRepository.findByIdAndIsBookedFalseWithLock(ticketId)
        .orElseThrow(
            () -> new CustomException("Ticket is already booked or not available", HttpStatus.NOT_FOUND.value()));

    // Ensure user has enough balance
    if (user.getBalance().compareTo(ticket.getPrice()) < 0) {
      throw new CustomException("Insufficient balance", HttpStatus.BAD_REQUEST.value());
    }

    // Deduct balance from user
    BigDecimal ticketPrice = ticket.getPrice();
    user.setBalance(user.getBalance().subtract(ticketPrice));
    userRepository.save(user);

    // Record the transaction
    Transaction transaction = new Transaction();
    transaction.setUser(user);
    transaction.setAmount(ticketPrice);
    transaction.setTransactionType(TransactionType.DEBIT); // Since it's a deduction
    transactionRepository.save(transaction);

    // Book the ticket
    ticket.setIsBooked(true);
    ticketRepository.save(ticket);

    // Create booking record
    Booking booking = new Booking();
    booking.setUser(user);
    booking.setTicket(ticket);
    bookingRepository.save(booking);

    return BookingMapper.INSTANCE.toDTO(booking);
  }
}
