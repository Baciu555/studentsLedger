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

import com.baciu.converter.LectureConverter;
import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.service.LectureService;

@RestController
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	@GetMapping("lectures")
	public ResponseEntity<List<Lecture>> getLectures() {
		return null;
	}
	
	@GetMapping("lectures/{id}")
	public ResponseEntity<LectureDTO> getLecture(@PathVariable("id") Long id) {
		LectureConverter lectureConverter = new LectureConverter();
		Lecture lecture = lectureService.getLecture(id);
		LectureDTO lectureDTO = lectureConverter.toDTOWithEntities(lecture);
		return null;
	}
	
	@PostMapping("lectures")
	public ResponseEntity<LectureDTO> addLecture(@RequestBody Lecture lecture) {
		return null;
	}
	
	@PutMapping("lectures")
	public ResponseEntity<LectureDTO> updateLecture(@RequestBody Lecture lecture) {
		return null;
	}
	
	@DeleteMapping("lectures")
	public ResponseEntity<String> deleteLecture(@RequestBody Lecture lecture) {
		return null;
	}

}
