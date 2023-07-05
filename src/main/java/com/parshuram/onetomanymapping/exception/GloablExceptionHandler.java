package com.parshuram.onetomanymapping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parshuram.onetomanymapping.entity.ApiResponse;

@RestControllerAdvice
public class GloablExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundExcepton.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundExcepton excepton){
	
		String message = excepton.getMessage();
		
		ApiResponse response=new ApiResponse(message,true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);	
	}

}
