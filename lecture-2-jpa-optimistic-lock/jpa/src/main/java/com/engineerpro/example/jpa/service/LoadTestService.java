package com.engineerpro.example.jpa.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoadTestService {

    public void performLoadTest(int maxUser, int ticketId) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxUser);
        RestTemplate restTemplate = new RestTemplate();

        for (int i = 1; i <= maxUser; i++) {
            String url = String.format("http://localhost:8080/api/bookings/book-ticket?userId=%d&ticketId=%d", i,
                    ticketId);
            executorService.submit(() -> {
                try {
                    String response = restTemplate.postForObject(url, null, String.class);
                    log.info("Response: " + response);
                } catch (Exception e) {
                    log.error("Response: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}