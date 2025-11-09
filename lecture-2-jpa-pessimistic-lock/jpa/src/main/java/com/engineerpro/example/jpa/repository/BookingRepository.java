package com.engineerpro.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerpro.example.jpa.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {}

