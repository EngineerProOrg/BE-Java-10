# JPA Optimistic Lock Demo - Ticket Booking System

This Spring Boot application demonstrates the implementation of optimistic locking in a ticket booking system. The system handles concurrent ticket bookings while maintaining data consistency using JPA's optimistic locking mechanism.

## Core Features

### 1. Ticket Management
- Tickets have properties like seat number, price, and booking status
- Uses optimistic locking (@Version) to handle concurrent booking attempts
- Prevents double-booking of tickets

### 2. Booking System
- Allows users to book available tickets
- Implements concurrent booking protection
- Handles booking conflicts using optimistic locking
- Returns appropriate responses for successful bookings and conflicts

### 3. Transaction Management
- Tracks all financial transactions in the system
- Supports two types of transactions:
  * DEBIT: When users book tickets
  * CREDIT: For refunds and top-ups
- Maintains transaction history per user

### 4. User Management
- Manages user accounts
- Associates bookings and transactions with users

## API Endpoints

### Bookings
```
POST /api/bookings/book-ticket
Parameters:
- userId: Long
- ticketId: Long
```

### Transactions
```
GET /api/transactions/user/{userId}
Returns: List of user's transactions
```

## Technical Implementation

### Optimistic Locking
The system uses JPA's optimistic locking to handle concurrent ticket bookings:
- `@Version` annotation on the Ticket entity
- Prevents race conditions in ticket booking
- Returns 429 (Too Many Requests) when concurrent booking conflicts occur

### Entity Relationships
- User -> Bookings: One-to-Many
- User -> Transactions: One-to-Many
- Ticket -> Booking: One-to-Many

### Data Models

#### Ticket
- id: Long
- seat: String
- price: BigDecimal
- isBooked: Boolean
- version: Integer (for optimistic locking)

#### Booking
- id: Long
- user: User
- ticket: Ticket
- bookingTime: Timestamp

#### Transaction
- id: Long
- user: User
- amount: BigDecimal
- transactionType: DEBIT/CREDIT
- transactionTime: Timestamp

## Error Handling
- Optimistic lock exceptions for concurrent bookings
- Custom exceptions for already booked tickets
- Global exception handling for consistent error responses

## Technologies Used
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- H2/MySQL Database (based on configuration)