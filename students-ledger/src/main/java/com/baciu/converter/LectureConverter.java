package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;

@Service
public class LectureConverter {

	@Autowired
	private SubjectConverter subjectConverter;

	@Autowired
	private TeacherConverter teacherConverter;

	@Autowired
	private StudentConverter studentConverter;

	public Set<LectureDTO> toDTO(Iterable<Lecture> lectures) {
		Set<LectureDTO> lecturesDTO = new HashSet<>();

		for (Lecture lecture : lectures)
			lecturesDTO.add(toDTO(lecture));

		return lecturesDTO;
	}

	public Set<LectureDTO> toDTO(Set<Lecture> lectures) {
		Set<LectureDTO> lecturesDTO = new HashSet<>();

		for (Lecture lecture : lectures)
			lecturesDTO.add(toDTO(lecture));

		return lecturesDTO;
	}
	
	public Set<LectureDTO> toDTOWithSubjects(Iterable<Lecture> lectures) {
		Set<LectureDTO> lecturesDTO = new HashSet<>();

		for (Lecture lecture : lectures)
			lecturesDTO.add(toDTOWithSubjects(lecture));

		return lecturesDTO;
	}

	public LectureDTO toDTOWithSubjects(Lecture lecture) {
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
		lectureDTO.setSubject(subjectConverter.toDTO(lecture.getSubject()));
		return lectureDTO;
	}

	public LectureDTO toDTO(Lecture lecture) {
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
		return lectureDTO;
	}

	public LectureDTO toDTOWithEntities(Lecture lecture) {
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
		lectureDTO.setSubject(subjectConverter.toDTO(lecture.getSubject()));
		lectureDTO.setTeacher(teacherConverter.toDTO(lecture.getTeacher()));
		lectureDTO.setStudents(studentConverter.toDTO(lecture.getStudents()));
		return lectureDTO;
	}

}
