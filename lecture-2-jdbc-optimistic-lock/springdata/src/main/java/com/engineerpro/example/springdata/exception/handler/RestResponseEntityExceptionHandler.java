package com.engineerpro.example.springdata.exception.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.engineerpro.example.springdata.exception.TicketBookedByAnother;

@RestControllerAdvice()
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String ERROR_CODE_INTERNAL = "INTERNAL_ERROR";
	private static final Map<Class<? extends RuntimeException>, HttpStatus> EXCEPTION_TO_HTTP_STATUS_CODE = Map.of(
			TicketBookedByAnother.class, HttpStatus.CONFLICT);

	private static final Map<Class<? extends RuntimeException>, String> EXCEPTION_TO_ERROR_CODE = Map.of(
			TicketBookedByAnother.class, "TICKET_BOOKED_BY_ANOTHER");

	@ExceptionHandler()
	ResponseEntity<ApiExceptionResponse> handleUserNotFoundException(RuntimeException exception) {
		HttpStatus httpStatus = EXCEPTION_TO_HTTP_STATUS_CODE.getOrDefault(exception.getClass(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		String errorCode = EXCEPTION_TO_ERROR_CODE.getOrDefault(exception.getClass(), ERROR_CODE_INTERNAL);

		if (errorCode.equals(ERROR_CODE_INTERNAL)) {
			// Log the error with stack trace
			logger.error("Exception occurred: ", exception);
		}

		final ApiExceptionResponse response = ApiExceptionResponse.builder().status(httpStatus).errorCode(errorCode)
				.build();

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}