package com.texas.exception;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LocationServiceExceptionHandler extends RuntimeException {

	public LocationServiceExceptionHandler(String message, Throwable e) {
		super(message,e);
	}
}
