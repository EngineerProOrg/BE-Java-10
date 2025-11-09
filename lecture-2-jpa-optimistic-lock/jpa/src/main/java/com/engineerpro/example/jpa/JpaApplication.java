package com.engineerpro.example.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args);
	}

}
