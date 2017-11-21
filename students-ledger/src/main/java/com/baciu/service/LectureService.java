package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Lecture;
import com.baciu.repository.LectureRepository;

@Service
public class LectureService {
	
	@Autowired
	private LectureRepository lectureRepository;
	
	public List<Lecture> getAll() {
		return (List<Lecture>) lectureRepository.findAll();
	}
	
	public Lecture getLecture(Long id) {
		return lectureRepository.findOne(id);
	}
	
	public Lecture addLecture(Lecture lecture) {
		return lectureRepository.save(lecture);
	}
	
	public Lecture updateLecture(Lecture lecture) {
		return lectureRepository.save(lecture);
	}
	
	public void deleteLecture(Lecture lecture) {
		lectureRepository.delete(lecture);
	}

}
