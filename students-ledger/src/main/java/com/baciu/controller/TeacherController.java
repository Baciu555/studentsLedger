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

import com.baciu.entity.Teacher;
import com.baciu.service.TeacherService;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("teachers")
	public ResponseEntity<List<Teacher>> getTeachers() {
		return null;
	}
	
	@GetMapping("teachers/{id}")
	public ResponseEntity<Teacher> getTeacher(@PathVariable("id") Long id) {
		return null;
	}
	
	@GetMapping("teachers/{id}/lectures")
	public ResponseEntity<Teacher> getTeacherLectures(@PathVariable("id") Long id) {
		return null;
	}
	
	@PostMapping("teachers")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
		return null;
	}
	
	@PutMapping("teachers")
	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
		return null;
	}
	
	@DeleteMapping("teachers")
	public ResponseEntity<String> deleteTeacher(@RequestBody Teacher teacher) {
		return null;
	}

}
