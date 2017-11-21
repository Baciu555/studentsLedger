package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.entity.Lecture;
import com.baciu.service.LectureService;

@RestController
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	@GetMapping("lectures")
	public List<Lecture> getLectures() {
		return lectureService.getAll();
	}
	
	@GetMapping("lectures/{id}")
	public Lecture getLecture(@PathVariable("id") Long id) {
		return lectureService.getLecture(id);
	}
	
	@PostMapping("lectures")
	public Lecture addLecture(@RequestBody Lecture lecture) {
		return lectureService.addLecture(lecture);
	}
	
	@PutMapping("lectures")
	public Lecture updateLecture(@RequestBody Lecture lecture) {
		return lectureService.updateLecture(lecture);
	}
	
	@DeleteMapping("lectures")
	public void deleteLecture(@RequestBody Lecture lecture) {
		lectureService.deleteLecture(lecture);
	}

}
