package com.engineerpro.example.springdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.engineerpro.example.springdata.model.Ticket;
import com.engineerpro.example.springdata.model.User;
import com.engineerpro.example.springdata.repository.BookingRepository;
import com.engineerpro.example.springdata.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;
  private BookingRepository bookingRepository;

  public UserService(UserRepository userRepository, BookingRepository bookingRepository) {
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
  }

  public void insertHundredUsers() {
    List<User> users = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      String username = "user" + i;
      double balance = 100.00;
      users.add(new User(username, balance));
    }

    userRepository.insertUsers(users);
  }

  public List<Ticket> getTicketsBookedByUser(int userId) {
    return bookingRepository.getTicketsBookedByUser(userId);
  }

}
