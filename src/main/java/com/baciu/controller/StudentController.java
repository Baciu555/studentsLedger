package com.baciu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.converter.StudentConverter;
import com.baciu.dto.StudentDTO;
import com.baciu.entity.Student;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.StudentNotExistsException;
import com.baciu.service.StudentService;

@RestController
@CrossOrigin(origins="*")
public class StudentController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentConverter studentConverter;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("students")
	public ResponseEntity<?> getAll() {
		Iterable<Student> students = studentService.getAll();
		
		return new ResponseEntity<>(studentConverter.toDTO(students), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("students/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
		Student student = studentService.getStudent(id);
		if (student == null)
			return new ResponseEntity<>("student not found", HttpStatus.BAD_REQUEST);
		
		System.out.println(student.getCourse() + " " + student.getSemester());
		return new ResponseEntity<>(studentConverter.toDTO(student), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("students")
	public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDTO studentDTO) throws EmailExistsException {
		Student student = studentService.addStudent(studentConverter.toEntity(studentDTO));
		
		LOG.info("student added");
		return new ResponseEntity<>(studentConverter.toDTO(student), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PutMapping("students")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDTO studentDTO) throws EmailExistsException {
		Student student = studentService.updateStudent(studentConverter.toEntity(studentDTO));
			
		LOG.info("student updated");
		return new ResponseEntity<>(studentConverter.toDTO(student), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) throws StudentNotExistsException {
		studentService.deleteStudent(id);
		
		LOG.info("user deleted");
		return new ResponseEntity<>("user deleted", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("login")
	public ResponseEntity<?> login() {
		LOG.info("user logged");
		return new ResponseEntity<>("{\"response\":\"OK\"}", HttpStatus.OK);
	}

}
