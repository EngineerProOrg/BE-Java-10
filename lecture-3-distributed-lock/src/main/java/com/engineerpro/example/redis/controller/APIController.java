package com.engineerpro.example.redis.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.example.redis.service.DistributedLockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class APIController {

	private DistributedLockService distributedLockService;

	public APIController(DistributedLockService distributedLockService) {
		this.distributedLockService = distributedLockService;
	}

	@GetMapping("/inspect")
	public String inspect(@RequestHeader("X-API-KEY") String apiKey) throws InterruptedException {
		String lockKey = apiKey;
		// avoid 1 user send duplicated requests
		// lockKey = userId
		// lockey = "lock:user_id:{-----}"
		
		// avoid multiple users book a same ticket
		// lockKey = ticketId 
		if (distributedLockService.acquireLock(lockKey)) {
			try {
				log.info("acquired lock for key: {}", lockKey);
				TimeUnit.SECONDS.sleep(10);
				// Perform the critical section of the code
				return "Operation performed successfully!";
			} finally {
				distributedLockService.releaseLock(lockKey);
				log.info("released lock for key: {}", lockKey);
			}
		} else {
			log.info("could not acquire lock for key: {}", lockKey);
			return "Could not acquire lock, please try again later.";
		}

	}

}
