package com.baciu.controller;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;
import com.baciu.service.TeacherService;

@RestController
@CrossOrigin(origins="*")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	private static Logger logger = LogManager.getLogger(TeacherController.class);
	
	@GetMapping("teachers")
	public ResponseEntity<?> getTeachers() {
		logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
		return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("teachers/{id}")
	public ResponseEntity<?> getTeacher(@PathVariable("id") Long id) {
		TeacherDTO teacherDTO = teacherService.getTeacher(id);
		if (teacherDTO == null)
			return new ResponseEntity<>("teacher not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@GetMapping("teachers/{id}/lectures")
	public ResponseEntity<?> getTeacherLectures(@PathVariable("id") Long id) {
		TeacherDTO teacherDTO = teacherService.getTeacherLectures(id);
		if (teacherDTO == null)
			return new ResponseEntity<>("teacher not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@PostMapping("teachers")
	public ResponseEntity<?> addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		TeacherDTO teacherDTO = new TeacherDTO();
		try {
			teacherDTO = teacherService.addTeacher(teacher);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
	}
	
	@PutMapping("teachers")
	public ResponseEntity<?> updateTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(teacherService.updateTeacher(teacher), HttpStatus.OK);
	}
	
	@DeleteMapping("teachers")
	public ResponseEntity<?> deleteTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		teacherService.deleteTeacher(teacher);
		return new ResponseEntity<>("teacher deleted", HttpStatus.OK);
	}

}
