package com.baciu.validation;

import org.springframework.stereotype.Service;

@Service
public class FieldValidator {
	
	public boolean validatePassword(String password) {
		return password.length() >= 3 && password.length() <= 50 ? true : false;
	}

}
