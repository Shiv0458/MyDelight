package com.MyDelight.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DelightExceptionHandler {
	
	@ExceptionHandler(DelightException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<DelightErrorResponse> exception(DelightException ex, WebRequest request) {
		DelightErrorResponse ders = new DelightErrorResponse(HttpStatus.BAD_REQUEST.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<DelightErrorResponse>(ders, HttpStatus.BAD_REQUEST);
	}


	
	
}
