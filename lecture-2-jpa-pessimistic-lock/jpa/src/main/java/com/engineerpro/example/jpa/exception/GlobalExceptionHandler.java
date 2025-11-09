package com.engineerpro.example.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.engineerpro.example.jpa.exception.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatusCode());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
  }
}
