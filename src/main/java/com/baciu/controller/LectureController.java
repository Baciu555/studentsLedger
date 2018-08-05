package com.baciu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.converter.LectureConverter;
import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.exception.LectureNotExistsException;
import com.baciu.service.LectureService;

@RestController
@CrossOrigin(origins="*")
public class LectureController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private LectureConverter lectureConverter;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("lectures")
	public ResponseEntity<?> getLectures() {
		return new ResponseEntity<>(lectureConverter.toDTO(lectureService.getAll()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("lectures/{id}")
	public ResponseEntity<?> getLecture(@PathVariable("id") Long id) {
		Lecture lecture = lectureService.getLecture(id);
		if (lecture == null)
			return new ResponseEntity<>("lecture not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(lectureConverter.toDTO(lecture), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("lectures")
	public ResponseEntity<?> addLecture(@Valid @RequestBody LectureDTO lectureDTO) {
		Lecture lecture = lectureService.addLecture(lectureConverter.toEntity(lectureDTO));
		
		LOG.info("lecture added");
		return new ResponseEntity<>(lectureConverter.toDTO(lecture), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("lectures")
	public ResponseEntity<?> updateLecture(@Valid @RequestBody LectureDTO lectureDTO) {	
		Lecture lecture = lectureService.updateLecture(lectureConverter.toEntity(lectureDTO));

		LOG.info("lecture updated");
		return new ResponseEntity<>(lectureConverter.toDTO(lecture), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("lectures/{id}")
	public ResponseEntity<?> deleteLecture(@PathVariable Long id) throws LectureNotExistsException {
		lectureService.deleteLecture(id);
		
		LOG.info("lecture deleted");
		return new ResponseEntity<>("lecture deleted", HttpStatus.OK);
	}

}
