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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.service.LectureService;

@RestController
@CrossOrigin(origins="*")
public class LectureController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LectureService lectureService;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("lectures")
	public ResponseEntity<?> getLectures() {
		return new ResponseEntity<>(lectureService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("lectures/subject")
	public ResponseEntity<?> getLecturesSubject() {
		return new ResponseEntity<>(lectureService.getAllWithSubjects(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("lectures/{id}")
	public ResponseEntity<?> getLecture(@PathVariable("id") Long id) {
		LectureDTO lectureDTO = lectureService.getLecture(id);
		if (lectureDTO == null)
			return new ResponseEntity<>("lecture not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("lectures")
	public ResponseEntity<?> addLecture(@Valid @RequestBody Lecture lecture, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("add lecture failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
			
		
		LectureDTO lectureDTO = new LectureDTO();
		try {
			lectureDTO = lectureService.addLecture(lecture);
		} catch (Exception e) {
			LOG.error("add lecture failed", e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		LOG.info("lecture added");
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("lectures")
	public ResponseEntity<?> updateLecture(@Valid @RequestBody Lecture lecture, Errors errors) {
		if (errors.hasErrors()) {
			LOG.error("update lecture failed", errors.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
			
		LectureDTO lectureDTO = lectureService.updateLecture(lecture);
		if (lectureDTO == null) {
			LOG.error("update lecture failed", "lecture already exists");
			return new ResponseEntity<>("lecture already exists", HttpStatus.BAD_REQUEST);
		}
			
		
		LOG.info("lecture updated");
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("lectures/{id}")
	public ResponseEntity<?> deleteLecture(@PathVariable Long id) {
		lectureService.deleteLecture(id);
		LOG.info("lecture deleted");
		return new ResponseEntity<>("lecture deleted", HttpStatus.OK);
	}

}
