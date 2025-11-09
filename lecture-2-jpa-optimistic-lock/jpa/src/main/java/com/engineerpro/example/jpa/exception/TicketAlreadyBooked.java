package com.engineerpro.example.jpa.exception;

public class TicketAlreadyBooked extends RuntimeException {
    public TicketAlreadyBooked(String message) {
        super(message);
    }
}
