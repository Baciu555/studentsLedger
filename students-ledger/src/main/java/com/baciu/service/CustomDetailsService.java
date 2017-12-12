package com.baciu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baciu.dto.StudentDTO;
import com.baciu.entity.CurrentUser;

@Service
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	private StudentService studentService;
	
	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		StudentDTO student = studentService.getByEmail(email);
		
		if (student == null) {
			throw new UsernameNotFoundException("student not found");
		}
		
		return new CurrentUser(student);
	}

}
