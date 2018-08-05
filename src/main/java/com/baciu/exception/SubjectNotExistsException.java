package com.baciu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="subject does not exists")
public class SubjectNotExistsException extends Exception {
	
}
