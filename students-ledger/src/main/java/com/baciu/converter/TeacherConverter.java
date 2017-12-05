package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;

@Service
public class TeacherConverter {
	
	@Autowired
	private LectureConverter lectureConverter;
	
	public TeacherDTO toDTO(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setSurname(teacher.getSurname());
		teacherDTO.setSalary(teacher.getSalary());
		
		return teacherDTO;
	}

	public TeacherDTO toDTOLectures(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setSurname(teacher.getSurname());
		teacherDTO.setSalary(teacher.getSalary());
		teacherDTO.setLectures(lectureConverter.toDTO(teacher.getLectures()));
		
		return teacherDTO;
	}
	
	public Set<TeacherDTO> toDTO(Set<Teacher> teachers) {
		Set<TeacherDTO> teachersDTO = new HashSet<TeacherDTO>();

		for (Teacher teacher : teachers)
			teachersDTO.add(toDTO(teacher));

		return teachersDTO;
	}
	
	public Set<TeacherDTO> toDTO(Iterable<Teacher> teachers) {
		Set<TeacherDTO> teachersDTO = new HashSet<TeacherDTO>();

		for (Teacher teacher : teachers)
			teachersDTO.add(toDTO(teacher));

		return teachersDTO;
	}
	
}
