package com.baciu.converter;

import com.baciu.dto.LectureDTO;
import com.baciu.entity.Lecture;

public class LectureConverter {
	
	public LectureDTO toDTO(Lecture lecture) {
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
		return lectureDTO;
	}
	
	public LectureDTO toDTOWithEntities(Lecture lecture) {
		SubjectConverter subjectConverter = new SubjectConverter();
		TeacherConverter teacherConverter = new TeacherConverter();
		StudentConverter studentConverter = new StudentConverter();
		
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
