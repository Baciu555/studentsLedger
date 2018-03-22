package com.baciu.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.baciu.dto.LectureDTO;
import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Lecture;
import com.baciu.entity.Teacher;

@Component
public class TeacherConverter implements SimpleConverter<Teacher, TeacherDTO> {

	@Override
	public TeacherDTO toDTO(Teacher source) {
		TeacherDTO teacherDTO = TeacherDTO.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.password(source.getPassword())
				.email(source.getEmail())
				.salary(source.getSalary()).build();
		
		if(source.getLectures() != null) {
			Set<LectureDTO> lectures = new HashSet<>(0);
			source.getLectures()
				.forEach(l -> lectures.add(LectureDTO.builder().id(l.getId())
						.classNumber(l.getClassNumber()).date(l.getDate()).subject(l.getSubject())
						.build()));
			teacherDTO.setLectures(lectures);
		}
		
		return teacherDTO;
	}

	@Override
	public Teacher toEntity(TeacherDTO source) {
		System.out.println(source);
		Teacher teacher = Teacher.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.email(source.getEmail())
				.password(source.getPassword())
				.salary(source.getSalary()).build();
		
		if(source.getLectures() != null) {
			Set<Lecture> lectures = new HashSet<>(0);
			source.getLectures()
				.forEach(l -> lectures.add(Lecture.builder().id(l.getId())
						.classNumber(l.getClassNumber()).date(l.getDate()).subject(l.getSubject())
						.build()));
			teacher.setLectures(lectures);
		}
		
		System.out.println(teacher);
		
		return teacher;
	}

	@Override
	public Iterable<TeacherDTO> toDTO(Iterable<Teacher> source) {
		List<Teacher> teachers = (List<Teacher>)source;
		return teachers.stream().map(t -> toDTO(t)).collect(Collectors.toList());
	}

	@Override
	public Iterable<Teacher> toEntity(Iterable<TeacherDTO> source) {
		List<TeacherDTO> teachers = (List<TeacherDTO>)source;
		return teachers.stream().map(t -> toEntity(t)).collect(Collectors.toList());
	}

}
