package com.baciu.converter;

import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;

public class TeacherConverter {
	
	public TeacherDTO toDTO(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setSurname(teacher.getSurname());
		teacherDTO.setSalary(teacher.getSalary());
		return teacherDTO;
	}

}
