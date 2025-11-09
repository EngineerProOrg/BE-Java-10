package com.engineerpro.example.jpa.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerpro.example.jpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // User findByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndUsername(Long id, String username);
}
