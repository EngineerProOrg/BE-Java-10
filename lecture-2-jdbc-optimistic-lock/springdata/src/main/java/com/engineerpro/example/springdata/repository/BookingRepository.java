package com.engineerpro.example.springdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.engineerpro.example.springdata.model.Booking;
import com.engineerpro.example.springdata.model.Ticket;
import com.engineerpro.example.springdata.model.Transaction;
import com.engineerpro.example.springdata.model.User;

@Repository
public class BookingRepository {
    private JdbcTemplate jdbcTemplate;

    public BookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for User
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getDouble("balance"));
    };

    // RowMapper for Ticket
    private RowMapper<Ticket> ticketRowMapper = (rs, rowNum) -> {
        return new Ticket(
                rs.getInt("id"),
                rs.getString("seat"),
                rs.getDouble("price"),
                rs.getBoolean("is_booked"),
                rs.getInt("version"));
    };

    // RowMapper for Booking
    private RowMapper<Booking> bookingRowMapper = (rs, rowNum) -> {
        User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getDouble("balance"));
        Ticket ticket = new Ticket(rs.getInt("ticket_id"), rs.getString("seat"), rs.getDouble("price"),
                rs.getBoolean("is_booked"), rs.getInt("version"));
        return new Booking(rs.getInt("id"), user, ticket, rs.getTimestamp("booking_time"));
    };

    public Optional<Ticket> getTicketById(int ticketId) {
        String sql = "SELECT * FROM tickets WHERE id = ? AND is_booked = false";
        try {
            Ticket ticket = jdbcTemplate.queryForObject(sql, ticketRowMapper, ticketId);
            return Optional.of(ticket);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int markTicketAsBooked(int ticketId, int version) {
        String sql = "UPDATE tickets SET is_booked = true, version = version + 1 WHERE id = ? AND version = ?";
        return jdbcTemplate.update(sql, ticketId, version);
    }

    public Optional<User> getUserByIdForUpdate(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, userId);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void updateUserBalance(int userId, double newBalance) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        jdbcTemplate.update(sql, newBalance, userId);
    }

    public void insertBooking(int userId, int ticketId) {
        String sql = "INSERT INTO bookings (user_id, ticket_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, ticketId);
    }

    // Method to save a transaction
    public void saveTransaction(int userId, double amount, String transactionType) {
        String sql = "INSERT INTO transactions (user_id, amount, transaction_type) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, amount, transactionType);
    }

    // Optional: Get all transactions for a user
    public List<Transaction> getTransactionsByUserId(int userId) {
        String sql = "SELECT * FROM transactions WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Transaction(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getDouble("amount"),
                rs.getString("transaction_type"),
                rs.getTimestamp("transaction_time")), userId);
    }

    public List<Ticket> getTicketsBookedByUser(int userId) {
        String sql = "SELECT t.* FROM tickets t " +
                "INNER JOIN bookings b ON t.id = b.ticket_id " +
                "WHERE b.user_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setSeat(rs.getString("seat"));
            ticket.setPrice(rs.getDouble("price"));
            ticket.setBooked(rs.getBoolean("is_booked"));
            return ticket;
        }, userId);

    }

}
