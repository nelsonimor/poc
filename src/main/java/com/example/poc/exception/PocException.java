package com.example.poc.exception;

import org.springframework.http.HttpStatus;

public class PocException extends RuntimeException {

    private String code;
    private Object[] params;
    private HttpStatus httpStatus;
    
	public PocException(String message) {
		super(message);
	}
	
	public PocException(String code,Object[] params,HttpStatus httpStatus) {
		super();
		this.code = code;
		this.params = params;
		this.httpStatus = httpStatus;
	}
     
	public String getCode() {
		return code;
	}
	public Object[] getParams() {
		return params;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


    
    
    
}
