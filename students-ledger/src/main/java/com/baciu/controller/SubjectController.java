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

import com.baciu.dto.SubjectDTO;
import com.baciu.entity.Subject;
import com.baciu.exception.SubjectExistsException;
import com.baciu.service.SubjectService;

@RestController
@CrossOrigin(origins="*")
public class SubjectController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SubjectService subjectService;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("subjects")
	public ResponseEntity<?> getSubjects() {
		return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("subjects/{id}")
	public ResponseEntity<?> getSubject(@PathVariable("id") Long id) {
		SubjectDTO subjectDTO = subjectService.getSubject(id);
		if (subjectDTO == null)
			return new ResponseEntity<>("subject not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("subjects/{id}/lectures")
	public ResponseEntity<?> getSubjectLectures(@PathVariable("id") Long id) {
		SubjectDTO subjectDTO = subjectService.getSubjectLectures(id);
		if (subjectDTO == null)
			return new ResponseEntity<>("subject not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("subjects")
	public ResponseEntity<?> addSubject(@Valid @RequestBody Subject subject) throws SubjectExistsException {
		SubjectDTO subjectDTO = subjectService.addSubject(subject);
		
		LOG.info("subject added");
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("subjects")
	public ResponseEntity<?> updateSubject(@Valid @RequestBody Subject subject) throws SubjectExistsException {
		SubjectDTO subjectDTO = subjectService.updateSubject(subject);
		
		LOG.info("subject updated");
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}

}
