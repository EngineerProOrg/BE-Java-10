package com.engineerpro.example.springdata.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.engineerpro.example.springdata.model.User;

@Repository
public class UserRepository {
  private JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Method to insert a batch of users
  public void insertUsers(List<User> users) {
    String sql = "INSERT INTO users (username, balance) VALUES (?, ?)";

    // Batch insert
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        ps.setString(1, users.get(i).getUsername());
        ps.setDouble(2, users.get(i).getBalance());
      }

      @Override
      public int getBatchSize() {
        return users.size();
      }
    });
  }
}
