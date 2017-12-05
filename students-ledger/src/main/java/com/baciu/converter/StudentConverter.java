package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dto.StudentDTO;
import com.baciu.entity.Student;

@Service
public class StudentConverter {

	@Autowired
	private LectureConverter lectureConverter;

	public StudentDTO toDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setCourse(student.getCourse());

		return studentDTO;
	}

	public StudentDTO toDTOLectures(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setCourse(student.getCourse());
		studentDTO.setLectures(lectureConverter.toDTO(student.getLectures()));

		return studentDTO;
	}

	public Set<StudentDTO> toDTO(Set<Student> students) {
		Set<StudentDTO> studentsDTO = new HashSet<StudentDTO>();

		for (Student student : students)
			studentsDTO.add(toDTO(student));

		return studentsDTO;
	}

	public Set<StudentDTO> toDTO(Iterable<Student> students) {
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
