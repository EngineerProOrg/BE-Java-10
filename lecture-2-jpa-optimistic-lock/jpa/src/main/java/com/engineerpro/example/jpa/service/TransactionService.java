package com.engineerpro.example.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.engineerpro.example.jpa.model.Transaction;
import com.engineerpro.example.jpa.model.User;
import com.engineerpro.example.jpa.repository.TransactionRepository;
import com.engineerpro.example.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public List<Transaction> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }
}