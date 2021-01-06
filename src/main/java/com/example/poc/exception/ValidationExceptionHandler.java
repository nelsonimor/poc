package com.example.poc.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.poc.service.IEventCreatorService;

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private IEventCreatorService eventCreatorService;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			stringBuilder.append("["+error.getRejectedValue().toString()+"]["+error.getObjectName()+"]["+error.getField()+"]["+error.getDefaultMessage()+"].");
	    }

		String errorMessage = messageSource.getMessage("msg.TECH-002",new Object[] {ex.getBindingResult().getErrorCount(),stringBuilder.toString()},Locale.getDefault()); 
		eventCreatorService.createEventFailure("TECH-002",new Object[] {ex.getBindingResult().getErrorCount(),stringBuilder.toString()});
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessage);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}




}
