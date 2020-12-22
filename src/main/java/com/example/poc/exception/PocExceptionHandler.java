package com.example.poc.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PocExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(PocException.class)
	public ResponseEntity<ErrorResponse> handle(PocException e, Locale locale) {
		String errorMessage = messageSource.getMessage("msg."+e.getCode(),e.getParams(),locale);  
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(e.getHttpStatus().value());
		error.setMessage(errorMessage);
		return new ResponseEntity<>(error, e.getHttpStatus());
	}

}
