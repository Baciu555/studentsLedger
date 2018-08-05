package com.baciu.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.baciu.dto.LectureDTO;
import com.baciu.dto.StudentDTO;
import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Lecture;
import com.baciu.entity.Student;
import com.baciu.entity.Teacher;

@Component
public class LectureConverter implements SimpleConverter<Lecture, LectureDTO> {

	@Override
	public LectureDTO toDTO(Lecture source) {
		LectureDTO lectureDTO = LectureDTO.builder()
				.id(source.getId())
				.classNumber(source.getClassNumber())
				.date(source.getDate())
				.subject(source.getSubject()).build();
		
		if(source.getTeacher() != null) {
			Teacher teacher = source.getTeacher();
			lectureDTO.setTeacher(TeacherDTO.builder().id(teacher.getId())
					.name(teacher.getName()).surname(teacher.getSurname()).build());
		}
		
		if(source.getStudents() != null) {
			Set<StudentDTO> students = new HashSet<>(0);
			source.getStudents().forEach(s -> students.add(StudentDTO.builder()
					.id(s.getId())
					.name(s.getName())
					.surname(s.getSurname())
					.course(s.getCourse()).build()));
			lectureDTO.setStudents(students);
		}
		
		return lectureDTO;
	}

	@Override
	public Lecture toEntity(LectureDTO source) {
		Lecture lecture = Lecture.builder()
				.id(source.getId())
				.classNumber(source.getClassNumber())
				.date(source.getDate())
				.subject(source.getSubject()).build();
		
		if(source.getTeacher() != null) {
			TeacherDTO teacherDTO = source.getTeacher();
			lecture.setTeacher(Teacher.builder().id(teacherDTO.getId())
					.name(teacherDTO.getName()).surname(teacherDTO.getSurname()).build());
		}
		
		if(source.getStudents() != null) {
			Set<Student> students = new HashSet<>(0);
			source.getStudents().forEach(s -> students.add(Student.builder()
					.id(s.getId())
					.name(s.getName())
					.surname(s.getSurname())
					.course(s.getCourse()).build()));
			lecture.setStudents(students);
		}
		
		return lecture;
	}

	@Override
	public Iterable<LectureDTO> toDTO(Iterable<Lecture> source) {
		List<Lecture> lectures = (List<Lecture>) source;
		return lectures.stream().map(l -> toDTO(l)).collect(Collectors.toList());
	}

	@Override
	public Iterable<Lecture> toEntity(Iterable<LectureDTO> source) {
		List<LectureDTO> lectures = (List<LectureDTO> )source;
		return lectures.stream().map(l -> toEntity(l)).collect(Collectors.toList());
	}

}
