package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public List<Teacher> getTeachers() {
		return teacherService.getAll();
	}
	
	@GetMapping(value = "teachers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Teacher getTeacher(@PathVariable("id") Long id) {
		return teacherService.getTeacher(id);
	}
	
	@PostMapping("teachers")
	public Teacher addTeacher(@RequestBody Teacher teacher) {
		return teacherService.addTeacher(teacher);
	}
	
	@PutMapping("teachers")
	public Teacher updateTeacher(@RequestBody Teacher teacher) {
		return teacherService.updateTeacher(teacher);
	}
	
	@DeleteMapping("teachers")
	public void deleteTeacher(@RequestBody Teacher teacher) {
		teacherService.deteleTeacher(teacher);
	}

}
