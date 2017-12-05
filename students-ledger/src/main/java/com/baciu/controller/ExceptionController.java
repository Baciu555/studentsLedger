package com.baciu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> messageNotReadableExceptionHandle (HttpMessageNotReadableException e) {
		return new ResponseEntity<>("hehehe", HttpStatus.BAD_REQUEST);
	}

}
