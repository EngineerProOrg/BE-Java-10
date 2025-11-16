package com.engineerpro.example.redis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class APIController {

	@GetMapping("/inspect")
	public ResponseEntity inspect(@RequestHeader("X-API-KEY") String apiKey) {
		return ResponseEntity.status(HttpStatus.OK).body("API key " + apiKey + " is not limited by rate limiter");
	}

}
