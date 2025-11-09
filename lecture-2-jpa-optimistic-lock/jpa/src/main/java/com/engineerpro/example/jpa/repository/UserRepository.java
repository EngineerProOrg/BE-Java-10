package com.engineerpro.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerpro.example.jpa.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
