package com.engineerpro.example.jpa.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineerpro.example.jpa.dto.BookingDTO;
import com.engineerpro.example.jpa.exception.TicketAlreadyBooked;
import com.engineerpro.example.jpa.exception.TicketBookedByAnother;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

  @Transactional()
  public BookingDTO bookTicket(Long userId, Long ticketId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    // Use pessimistic lock to prevent concurrent booking of the same ticket
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new RuntimeException("Ticket not found"));
    if (ticket.getIsBooked()) {
      throw new TicketAlreadyBooked("Ticket is already booked");
    }

    // Ensure user has enough balance
    if (user.getBalance().compareTo(ticket.getPrice()) < 0) {
      throw new RuntimeException("Insufficient balance");
    }

    // Deduct balance from user
    BigDecimal ticketPrice = ticket.getPrice();
    user.setBalance(user.getBalance().subtract(ticketPrice));
    userRepository.save(user);

    // Record the transaction
    Transaction transaction = new Transaction();
    transaction.setUser(user);
    transaction.setAmount(ticketPrice);
    transaction.setTransactionType(TransactionType.DEBIT);
    transactionRepository.save(transaction);

    // Book the ticket
    // use optimistic
    int updatedRows = ticketRepository.updateTicketIsBooked(ticket.getId(), ticket.getVersion());
    log.info("user {} Updated rows: {}", userId, updatedRows);
    if (updatedRows == 0) {
      throw new TicketBookedByAnother("Ticket is booked by another user");
    }

    // Create booking record
    Booking booking = Booking.builder().user(user).ticket(ticket).build();
    bookingRepository.save(booking);

    return BookingMapper.INSTANCE.toDTO(booking);
  }
}
