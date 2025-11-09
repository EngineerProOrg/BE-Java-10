package com.engineerpro.example.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.jpa.service.LoadTestService;

@RestController
@RequestMapping("/tests")
public class LoadTestController {
    private LoadTestService loadTestService;

    public LoadTestController(LoadTestService loadTestService) {
        this.loadTestService = loadTestService;
    }

    @GetMapping("/load-test")
    public String loadTest(@RequestParam int maxUser, @RequestParam int ticketId) {
        loadTestService.performLoadTest(maxUser, ticketId);
        return "Load test started!";
    }
}
