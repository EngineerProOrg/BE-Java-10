package com.engineerpro.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerpro.example.jpa.model.Transaction;
import com.engineerpro.example.jpa.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
