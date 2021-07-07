package com.texas.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.texas.exception.LocationServiceExceptionHandler;
import com.texas.exception.MenuServiceExceptionHandler;
import com.texas.response.Response;
import com.texas.response.ResponseMetaData;
import com.texas.response.StatusMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MenuServiceExceptionHandler.class)
	public ResponseEntity<Response<?>> handleMenuServiceException(MenuServiceExceptionHandler exception){
		log.error(exception.getMessage());
		return buildResponse(StatusMessage.REQUEST_NOT_PROCESSED, HttpStatus.BAD_REQUEST ,exception.getMessage());
	}
	 @ExceptionHandler(LocationServiceExceptionHandler.class)
	public ResponseEntity<Response<?>> handleLocationServiceException(LocationServiceExceptionHandler exception){
		log.error(exception.getMessage());
		return buildResponse(StatusMessage.REQUEST_NOT_PROCESSED, HttpStatus.BAD_REQUEST ,exception.getMessage());
	}
	
	private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status, String errorMsg ){
		Response responseObj = Response.builder().responseMeta(ResponseMetaData.builder().
				message(statusMessage.name()).statusCode(status.value()).build())
				      .data(errorMsg).build();
		
		return ResponseEntity.status(status).body(responseObj);
	}
}
