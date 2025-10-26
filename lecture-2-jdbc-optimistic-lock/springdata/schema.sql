-- Create `users` table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

-- Create `tickets` table
CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seat VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_booked BOOLEAN DEFAULT FALSE,
    version INT DEFAULT 0
);

-- Create `bookings` table
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    ticket_id INT,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    amount DECIMAL(10, 2),
    transaction_type ENUM('DEBIT', 'CREDIT'),
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);