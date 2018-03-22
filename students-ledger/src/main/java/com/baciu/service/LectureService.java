package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Lecture;
import com.baciu.exception.LectureNotExistsException;
import com.baciu.repository.LectureRepository;

@Service
public class LectureService {
	
	@Autowired
	private LectureRepository lectureRepository;
	
	public Set<Lecture> getAll() {
		return (Set<Lecture>) lectureRepository.findAll();
	}
	
	public Lecture getLecture(Long id) {
		if (!lectureRepository.exists(id))
			return null;
		
		return lectureRepository.findOne(id);
	}
	
	public Lecture addLecture(Lecture lecture) {
		return lectureRepository.save(lecture);
	}
	
	public Lecture updateLecture(Lecture lecture) {
		return lectureRepository.save(lecture);
	}
	
	public void deleteLecture(Long id) throws LectureNotExistsException {
		if (lectureRepository.findOne(id) == null)
			throw new LectureNotExistsException();
		
		lectureRepository.delete(id);
	}

}
