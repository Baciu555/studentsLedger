package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.entity.Student;
import com.baciu.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("students")
	public List<Student> getAll() {
		return studentService.getAll();
	}
	
	@GetMapping("students/{id}")
	public Student getStudent(@PathVariable("id") Long id) {
		return studentService.getStudent(id);
	}
	
	@PostMapping("students")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@PutMapping("students")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping("students")
	public void deleteStudent(@RequestBody Student student) {
		studentService.deleteStudent(student);
	}
	
	

}
