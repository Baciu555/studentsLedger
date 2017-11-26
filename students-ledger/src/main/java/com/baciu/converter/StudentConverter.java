package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import com.baciu.dto.StudentDTO;
import com.baciu.entity.Student;

public class StudentConverter {
	
	public StudentDTO toDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setCourse(student.getCourse());
		
		return studentDTO;
	}
	
	public Set<StudentDTO> toDTO(Set<Student> students) {
		Set<StudentDTO> studentsDTO = new HashSet<StudentDTO>();
		
		for (Student student : students)
			studentsDTO.add(toDTO(student));
		
		return studentsDTO;
	}
	
	public Student toEntity(StudentDTO studentDTO) {
		Student student = new Student();
		student.setId(studentDTO.getId());
		student.setName(studentDTO.getName());
		student.setSurname(studentDTO.getSurname());
		student.setEmail(studentDTO.getEmail());
		student.setCourse(studentDTO.getCourse());
		
		return student;
	}

}
