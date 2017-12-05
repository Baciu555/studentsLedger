package com.baciu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.dto.SubjectDTO;
import com.baciu.entity.Subject;
import com.baciu.service.SubjectService;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("subjects")
	public ResponseEntity<?> getSubjects() {
		return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("subjects/{id}")
	public ResponseEntity<?> getSubject(@PathVariable("id") Long id) {
		SubjectDTO subjectDTO = subjectService.getSubject(id);
		if (subjectDTO == null)
			return new ResponseEntity<>("subject not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@GetMapping("subjects/{id}/lectures")
	public ResponseEntity<?> getSubjectLectures(@PathVariable("id") Long id) {
		SubjectDTO subjectDTO = subjectService.getSubjectLectures(id);
		if (subjectDTO == null)
			return new ResponseEntity<>("subject not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@PostMapping("subjects")
	public ResponseEntity<?> addSubject(@Valid @RequestBody Subject subject, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError(), HttpStatus.BAD_REQUEST);
		
		SubjectDTO subjectDTO = new SubjectDTO();
		try {
			subjectDTO = subjectService.addSubject(subject);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@PutMapping("subjects")
	public ResponseEntity<?> updateSubject(@Valid @RequestBody Subject subject, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError(), HttpStatus.BAD_REQUEST);
		
		SubjectDTO subjectDTO = subjectService.updateSubject(subject);
		if (subjectDTO == null)
			return new ResponseEntity<>("subject already exists", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("subjects")
	public ResponseEntity<?> deleteSubject(@Valid @RequestBody Subject subject, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		subjectService.deleteSubject(subject);
		return new ResponseEntity<>("subject deleted", HttpStatus.OK);
	}

}
