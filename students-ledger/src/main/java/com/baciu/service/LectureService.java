package com.baciu.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.LectureConverter;
import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;
import com.baciu.exception.LectureNotExistsException;
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
		lecture.setDate(new Date());
		return lectureConverter.toDTOWithEntities(lectureRepository.save(lecture));
	}
	
	public LectureDTO updateLecture(Lecture lecture) {
		return lectureConverter.toDTOWithEntities(lectureRepository.save(lecture));
	}
	
	public void deleteLecture(Long id) throws LectureNotExistsException {
		if (lectureRepository.findOne(id) == null)
			throw new LectureNotExistsException();
		
		lectureRepository.delete(id);
	}

}
