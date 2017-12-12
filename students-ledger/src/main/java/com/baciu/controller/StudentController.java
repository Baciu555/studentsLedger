package com.baciu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.dto.StudentDTO;
import com.baciu.entity.Student;
import com.baciu.service.StudentService;

@RestController
@CrossOrigin(origins="*")
public class StudentController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StudentService studentService;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("students")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("students/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
		StudentDTO studentDTO = studentService.getStudent(id);
		if (studentDTO == null)
			return new ResponseEntity<>("student not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("students/{id}/lectures")
	public ResponseEntity<?> getStudentLectures(@PathVariable("id") Long id) {
		StudentDTO studentDTO = studentService.getStudentLectures(id);
		if (studentDTO == null)
			return new ResponseEntity<>("student not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("students")
	public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("add student failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		StudentDTO studentDTO = new StudentDTO();
		try {
			studentDTO = studentService.addStudent(student);
		} catch (Exception e) {
			LOG.error("add student failed", e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		LOG.error("student added");
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PutMapping("students")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("update student failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
			
		
		StudentDTO studentDTO = studentService.updateStudent(student);
		if (studentDTO == null) {
			LOG.error("update student failed, email already exists");
			return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
		}
			
		LOG.info("student updated");
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		LOG.info("user deleted");
		return new ResponseEntity<>("user deleted", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("login")
	public ResponseEntity<?> login() {
		LOG.info("user logged");
		return new ResponseEntity<>("logged", HttpStatus.OK);
	}

}
