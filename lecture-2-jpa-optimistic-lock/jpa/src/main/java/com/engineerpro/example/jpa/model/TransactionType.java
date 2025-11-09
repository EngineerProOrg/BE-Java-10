package com.engineerpro.example.jpa.model;

public enum TransactionType {
  DEBIT, // Deducting balance (e.g., when a user books a ticket)
  CREDIT // Adding balance (e.g., when a user gets a refund or top-up)
}
