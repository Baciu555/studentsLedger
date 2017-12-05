package com.baciu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("students")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("students/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
		StudentDTO studentDTO = studentService.getStudent(id);
		if (studentDTO == null)
			return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@GetMapping("students/{id}/lectures")
	public ResponseEntity<?> getStudentLectures(@PathVariable("id") Long id) {
		StudentDTO studentDTO = studentService.getStudentLectures(id);
		if (studentDTO == null)
			return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PostMapping("students")
	public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		StudentDTO studentDTO = new StudentDTO();
		try {
			studentDTO = studentService.addStudent(student);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PutMapping("students")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		StudentDTO studentDTO = studentService.updateStudent(student);
		if (studentDTO == null)
			return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
			
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("students")
	public ResponseEntity<?> deleteStudent(@Valid @RequestBody Student student, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		studentService.deleteStudent(student);
		return new ResponseEntity<>("user deleted", HttpStatus.OK);
	}

}
