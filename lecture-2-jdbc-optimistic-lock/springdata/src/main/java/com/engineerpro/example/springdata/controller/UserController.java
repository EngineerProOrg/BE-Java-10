package com.engineerpro.example.springdata.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.springdata.model.Ticket;
import com.engineerpro.example.springdata.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertHundredUsers() {
        userService.insertHundredUsers();
        return ResponseEntity.ok("100 users inserted successfully!");
    }

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsBookedByUser(@PathVariable int userId) {
        List<Ticket> tickets = userService.getTicketsBookedByUser(userId);
        return ResponseEntity.ok(tickets);
    }
}
