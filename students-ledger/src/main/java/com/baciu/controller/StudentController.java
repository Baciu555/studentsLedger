package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.baciu.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("students")
	public ResponseEntity<List<Student>> getAll() {
		return null;
	}
	
	@GetMapping("students/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
		Student student = studentService.getStudent(id);
		StudentConverter studentConverter = new StudentConverter();
		StudentDTO studentDTO = studentConverter.toDTO(student);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<StudentDTO>(studentDTO, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("students/{id}/lectures")
	public ResponseEntity<StudentDTO> getStudentLectures(@PathVariable("id") Long id) {
		
		return null;
	}
	
	@PostMapping("students")
	public ResponseEntity<StudentDTO> addStudent(@RequestBody Student student) {
		return null;
	}
	
	@PutMapping("students")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody Student student) {
		return null;
	}
	
	@DeleteMapping("students")
	public ResponseEntity<String> deleteStudent(@RequestBody Student student) {
		return null;
	}
	
	

}
