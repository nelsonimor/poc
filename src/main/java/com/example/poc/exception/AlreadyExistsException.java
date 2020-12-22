package com.example.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends PocException {

	public AlreadyExistsException(String message) {
		super(message);
	}
	
	public AlreadyExistsException(String code,Object[] params) {
		super(code,params,HttpStatus.CONFLICT);
	}
		
}
