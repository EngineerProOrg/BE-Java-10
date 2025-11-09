package com.engineerpro.example.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.engineerpro.example.jpa.dto.BookingDTO;
import com.engineerpro.example.jpa.model.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {

  BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "ticket.id", target = "ticketId")
  BookingDTO toDTO(Booking booking);
}
