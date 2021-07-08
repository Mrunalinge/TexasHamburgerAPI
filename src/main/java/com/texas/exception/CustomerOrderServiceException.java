package com.texas.exception;

public class CustomerOrderServiceException extends RuntimeException {
	
	public CustomerOrderServiceException(String message, Throwable e) {
		super(message,e);
	}

}
