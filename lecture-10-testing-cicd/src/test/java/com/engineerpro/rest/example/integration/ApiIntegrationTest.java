package com.engineerpro.rest.example.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ApiIntegrationTest {
  WebTestClient client;

  @BeforeEach
  void setup() {
    client = WebTestClient.bindToServer().baseUrl("https://fast-retreat-96279-8842fd2be788.herokuapp.com").build();
  }

  @Test
  void testUserWithBalance() {
    client.get()
        .uri("/users/100/balance")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  void testUserNoExist() {
    client.get()
        .uri("/users/1001/balance")
        .exchange()
        .expectStatus().isNotFound();
  }
}
