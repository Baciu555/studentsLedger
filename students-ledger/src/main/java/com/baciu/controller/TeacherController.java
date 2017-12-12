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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;
import com.baciu.service.TeacherService;

@RestController
@CrossOrigin(origins="*")
public class TeacherController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TeacherService teacherService;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("teachers")
	public ResponseEntity<?> getTeachers() {
		return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("teachers/{id}")
	public ResponseEntity<?> getTeacher(@PathVariable("id") Long id) {
		TeacherDTO teacherDTO = teacherService.getTeacher(id);
		if (teacherDTO == null)
			return new ResponseEntity<>("teacher not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("teachers/{id}/lectures")
	public ResponseEntity<?> getTeacherLectures(@PathVariable("id") Long id) {
		TeacherDTO teacherDTO = teacherService.getTeacherLectures(id);
		if (teacherDTO == null)
			return new ResponseEntity<>("teacher not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("teachers")
	public ResponseEntity<?> addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("add teacher failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		TeacherDTO teacherDTO = new TeacherDTO();
		try {
			teacherDTO = teacherService.addTeacher(teacher);
		} catch (Exception e) {
			LOG.error("add teacher failed", e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		LOG.info("teacher added");
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("teachers")
	public ResponseEntity<?> updateTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("update teacher failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
			
		
		TeacherDTO teacherDTO = teacherService.updateTeacher(teacher);
		if (teacherDTO == null) {
			LOG.error("update teacher failed", "email already exists");
			return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
		}
			
		LOG.info("teacher updated");
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
}
