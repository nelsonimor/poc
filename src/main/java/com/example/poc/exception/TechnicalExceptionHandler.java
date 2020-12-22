package com.example.poc.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.poc.service.IEventCreatorService;

@ControllerAdvice
public class TechnicalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IEventCreatorService eventCreatorService;

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handle(DataIntegrityViolationException e, Locale locale) {
		eventCreatorService.createEvent("TECH-001",new Object[] {e.getMessage().toString().substring(0, 200)});
		String errorMessage = messageSource.getMessage("msg.TECH-001",new Object[] {e.getMessage().toString()},locale);  
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessage);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e, Locale locale) {
		eventCreatorService.createEvent("TECH-001",new Object[] {e.getMessage().toString().substring(0, 200)});
		String errorMessage = messageSource.getMessage("msg.TECH-001",new Object[] {e.getMessage().toString()},locale);  
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessage);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	


}
