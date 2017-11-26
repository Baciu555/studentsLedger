package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Subject>> getTeachers() {
		return null;
	}
	
	@GetMapping("subjects/{id}")
	public ResponseEntity<Subject> getTeacher(@PathVariable("id") Long id) {
		return null;
	}
	
	@GetMapping("subjects/{id}/lectures")
	public ResponseEntity<Subject> getTeacherLectures(@PathVariable("id") Long id) {
		return null;
	}
	
	@PostMapping("subjects")
	public ResponseEntity<Subject> addTeacher(@RequestBody Subject subject) {
		return null;
	}
	
	@PutMapping("subjects")
	public ResponseEntity<Subject> updateTeacher(@RequestBody Subject subject) {
		return null;
	}
	
	@DeleteMapping("subjects")
	public ResponseEntity<String> deleteTeacher(@RequestBody Subject subject) {
		return null;
	}

}
