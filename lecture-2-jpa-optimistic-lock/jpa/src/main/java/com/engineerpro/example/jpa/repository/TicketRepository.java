package com.engineerpro.example.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.engineerpro.example.jpa.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  @Query("SELECT t FROM Ticket t WHERE t.id = :ticketId")
  Optional<Ticket> findByIdAndIsBookedFalse(@Param("ticketId") Long ticketId);

  @Modifying
  @Query("update Ticket set isBooked = true, version = version + 1 where id = :id and version = :version")
  int updateTicketIsBooked(@Param(value = "id") long id, @Param(value = "version") int version);
}
