package com.engineerpro.example.springdata.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadTestService {

    public void performLoadTest(int maxUser, int ticketId) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxUser);
        RestTemplate restTemplate = new RestTemplate();

        for (int i = 1; i <= maxUser; i++) {
            String url = String.format("http://localhost:8080/api/bookings/book-ticket?userId=%d&ticketId=%d", i, ticketId);
            executorService.submit(() -> {
                try {
                    String response = restTemplate.postForObject(url, null, String.class);
                    System.out.println("Response: " + response);
                } catch (Exception e) {
                    System.err.println("Request failed: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}