package com.engineerpro.example.springdata.exception;

public class TicketBookedByAnother extends RuntimeException {
    public TicketBookedByAnother(String message) {
        super(message);
    }
}
