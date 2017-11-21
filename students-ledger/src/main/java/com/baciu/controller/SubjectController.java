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

import com.baciu.entity.Subject;
import com.baciu.service.SubjectService;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("subjects")
	public List<Subject> getTeachers() {
		return subjectService.getAll();
	}
	
	@GetMapping("subjects/{id}")
	public Subject getTeacher(@PathVariable("id") Long id) {
		return subjectService.getSubject(id);
	}
	
	@PostMapping("subjects")
	public Subject addTeacher(@RequestBody Subject subject) {
		return subjectService.addSubject(subject);
	}
	
	@PutMapping("subjects")
	public Subject updateTeacher(@RequestBody Subject subject) {
		return subjectService.updateSubject(subject);
	}
	
	@DeleteMapping("subjects")
	public void deleteTeacher(@RequestBody Subject subject) {
		subjectService.deleteSubject(subject);
	}

}
