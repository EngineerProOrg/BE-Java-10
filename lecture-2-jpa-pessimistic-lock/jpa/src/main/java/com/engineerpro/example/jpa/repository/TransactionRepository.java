package com.engineerpro.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerpro.example.jpa.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
