package com.example.poc.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends PocException {
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String code,Object[] param) {
		super(code,param,HttpStatus.NOT_FOUND);
	}

}
