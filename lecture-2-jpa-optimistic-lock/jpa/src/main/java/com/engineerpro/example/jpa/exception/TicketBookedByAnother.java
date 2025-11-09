package com.engineerpro.example.jpa.exception;

public class TicketBookedByAnother extends RuntimeException {
    public TicketBookedByAnother(String message) {
        super(message);
    }
}
