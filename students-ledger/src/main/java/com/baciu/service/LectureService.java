package com.baciu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.LectureConverter;
import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.repository.LectureRepository;

@Service
public class LectureService {
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private LectureConverter lectureConverter;
	
	public Set<LectureDTO> getAll() {
		return lectureConverter.toDTO(lectureRepository.findAll());
	}
	
	public LectureDTO getLecture(Long id) {
		if (!lectureRepository.exists(id))
			return null;
		
		return lectureConverter.toDTOWithEntities(lectureRepository.findOne(id));
	}
	
	public Set<LectureDTO> getAllWithSubjects() {
		return lectureConverter.toDTOWithSubjects(lectureRepository.findAll());
	}
	
	public LectureDTO addLecture(Lecture lecture) {
		lecture.setDate(LocalDateTime.now());
		System.out.println(lecture.toString());
		return lectureConverter.toDTOWithEntities(lectureRepository.save(lecture));
	}
	
	public LectureDTO updateLecture(Lecture lecture) {
		return lectureConverter.toDTOWithEntities(lectureRepository.save(lecture));
	}
	
	public void deleteLecture(Lecture lecture) {
		lectureRepository.delete(lecture);
	}

}
