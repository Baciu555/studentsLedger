package com.baciu.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.baciu.dto.LectureDTO;
import com.baciu.dto.StudentDTO;
import com.baciu.entity.Lecture;
import com.baciu.entity.Student;

@Component
public class StudentConverter implements SimpleConverter<Student, StudentDTO> {

	@Override
	public StudentDTO toDTO(Student source) {
		StudentDTO studentDTO = StudentDTO.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.email(source.getEmail())
				.password(source.getPassword())
				.course(source.getCourse()).build();
		
		if(source.getLectures() != null) {
			Set<LectureDTO> lectures = new HashSet<>(0);
			source.getLectures()
				.forEach(l -> lectures.add(LectureDTO.builder().id(l.getId())
						.classNumber(l.getClassNumber()).date(l.getDate()).subject(l.getSubject())
						.build()));
			studentDTO.setLectures(lectures);
		}
		
		return studentDTO;
	}

	@Override
	public Student toEntity(StudentDTO source) {
		Student student = Student.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.email(source.getEmail())
				.password(source.getPassword())
				.course(source.getCourse()).build();
		
		if(source.getLectures() != null) {
			Set<Lecture> lectures = new HashSet<>(0);
			source.getLectures()
				.forEach(l -> lectures.add(Lecture.builder().id(l.getId())
						.classNumber(l.getClassNumber()).date(l.getDate()).subject(l.getSubject())
						.build()));
			student.setLectures(lectures);
		}
		
		return student;
	}

	@Override
	public List<StudentDTO> toDTO(Iterable<Student> source) {
		List<Student> students = (List<Student>) source;
		return students.stream().map(s -> toDTO(s)).collect(Collectors.toList());
	}

	@Override
	public Iterable<Student> toEntity(Iterable<StudentDTO> source) {
		List<StudentDTO> students = (List<StudentDTO>) source;
		return students.stream().map(s -> toEntity(s)).collect(Collectors.toList());
	}

}
