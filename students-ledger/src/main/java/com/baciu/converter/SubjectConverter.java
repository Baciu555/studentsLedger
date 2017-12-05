package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dto.SubjectDTO;
import com.baciu.entity.Subject;

@Service
public class SubjectConverter {
	
	@Autowired
	private LectureConverter lectureConverter;
	
	public SubjectDTO toDTO(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setName(subject.getName());;
		return subjectDTO;
	}
	
	public Set<SubjectDTO> toDTO(Set<Subject> subjects) {
		Set<SubjectDTO> subjectsDTO = new HashSet<SubjectDTO>();
		
		for (Subject subject : subjects)
			subjectsDTO.add(toDTO(subject));
		
		return subjectsDTO;	
	}
	
	public Set<SubjectDTO> toDTO(Iterable<Subject> subjects) {
		Set<SubjectDTO> subjectsDTO = new HashSet<SubjectDTO>();
		
		for (Subject subject : subjects)
			subjectsDTO.add(toDTO(subject));
		
		return subjectsDTO;	
	}

	public SubjectDTO toDTOLectures(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setName(subject.getName());;
		subjectDTO.setLectures(lectureConverter.toDTO(subject.getLectures()));
		return subjectDTO;
	}

}
