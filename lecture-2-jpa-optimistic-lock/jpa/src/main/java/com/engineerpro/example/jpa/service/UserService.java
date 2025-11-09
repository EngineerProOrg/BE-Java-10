package com.engineerpro.example.jpa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.ap.internal.gem.BuilderGem;
import org.springframework.stereotype.Service;

import com.engineerpro.example.jpa.model.User;
import com.engineerpro.example.jpa.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void insertHundredUsers() {
    for (int i = 1; i <= 100; i++) {
      String username = "user" + i;
      User user = new User();
      user.setBalance(new BigDecimal(100.00));
      user.setUsername(username);
      userRepository.save(user);
    }

  }

}
