package com.engineerpro.example.springdata.service;

import java.util.Random;
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
                // Random random = new Random();
                // Generate a random time between 0 and 999 milliseconds
                // int randomSleepTime = random.nextInt(1000); // 1000 is exclusive

                // System.out.println("Sleeping for " + randomSleepTime + " milliseconds.");

                // try {
                //     // Sleep for the random time
                //     Thread.sleep(randomSleepTime);
                // } catch (InterruptedException e) {
                //     // Handle the exception
                //     System.err.println("Thread was interrupted: " + e.getMessage());
                // }

                System.out.println("Awake after sleeping.");
                try {
                    String response = restTemplate.postForObject(url, null, String.class);
                    log.info(String.format("restTemplate Success Url=%s: response=%s", url, response));
                } catch (Exception e) {
                    log.warn(String.format("restTemplate Fail Url=%s: response=%s", url, e.getMessage()));
                }
            });
        }

        executorService.shutdown();
    }
}