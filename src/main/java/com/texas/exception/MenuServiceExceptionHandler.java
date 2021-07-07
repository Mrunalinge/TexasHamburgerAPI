package com.texas.exception;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MenuServiceExceptionHandler extends RuntimeException {
	
	
	public MenuServiceExceptionHandler(String message, Throwable cause) {
		//this.response=response;
		super(message,cause);
		
	}
	

}
