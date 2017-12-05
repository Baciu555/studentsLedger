package com.baciu.controller;

import javax.validation.Valid;

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

import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.service.LectureService;

@RestController
@CrossOrigin(origins="*")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	@GetMapping("lectures")
	public ResponseEntity<?> getLectures() {
		return new ResponseEntity<>(lectureService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("lectures/subject")
	public ResponseEntity<?> getLecturesSubject() {
		return new ResponseEntity<>(lectureService.getAllWithSubjects(), HttpStatus.OK);
	}
	
	@GetMapping("lectures/{id}")
	public ResponseEntity<?> getLecture(@PathVariable("id") Long id) {
		LectureDTO lectureDTO = lectureService.getLecture(id);
		if (lectureDTO == null)
			return new ResponseEntity<>("lecture not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@PostMapping("lectures")
	public ResponseEntity<?> addLecture(@Valid @RequestBody Lecture lecture, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		LectureDTO lectureDTO = new LectureDTO();
		try {
			lectureDTO = lectureService.addLecture(lecture);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@PutMapping("lectures")
	public ResponseEntity<?> updateLecture(@Valid @RequestBody Lecture lecture, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		LectureDTO lectureDTO = lectureService.updateLecture(lecture);
		if (lectureDTO == null)
			return new ResponseEntity<>("lecture already exists", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("lectures")
	public ResponseEntity<?> deleteLecture(@Valid @RequestBody Lecture lecture, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		
		lectureService.deleteLecture(lecture);
		return new ResponseEntity<>("lecture deleted", HttpStatus.OK);
	}

}
