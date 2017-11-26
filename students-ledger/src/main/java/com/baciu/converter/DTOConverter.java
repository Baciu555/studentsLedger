package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;

import com.baciu.dto.LectureDTO;
import com.baciu.dto.StudentDTO;
import com.baciu.dto.SubjectDTO;
import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Lecture;
import com.baciu.entity.Student;
import com.baciu.entity.Subject;
import com.baciu.entity.Teacher;

public class DTOConverter {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public StudentDTO studentConvertToDTO(Student student) {
//		StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setSurname(student.getSurname());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setCourse(student.getCourse());
		
		return studentDTO;
	}
	
	public Set<StudentDTO> studentConvertToDTO(Set<Student> students) {
		Set<StudentDTO> studentsDTO = new HashSet<StudentDTO>();
		
		for (Student student : students) {
			studentsDTO.add(studentConvertToDTO(student));
		}
		
		return studentsDTO;
	}
	
	public LectureDTO lectureConvertToDTO(Lecture lecture) {
//		LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
//		lectureDTO.setSubject(lecture.getSubject());
//		lectureDTO.setTeacher(lecture.getTeacher());
//		lectureDTO.setStudents(lecture.getStudents());
		return lectureDTO;
	}
	
	public LectureDTO lectureConvertToDTOWithData(Lecture lecture) {
//		LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
		LectureDTO lectureDTO = new LectureDTO();
		lectureDTO.setId(lecture.getId());
		lectureDTO.setClassNumber(lecture.getClassNumber());
		lectureDTO.setDate(lecture.getDate());
		lectureDTO.setSubject(subjectConvertToDTO(lecture.getSubject()));
		lectureDTO.setTeacher(teacherConvertToDTO(lecture.getTeacher()));
		lectureDTO.setStudents(studentConvertToDTO(lecture.getStudents()));
		return lectureDTO;
	}

	private TeacherDTO teacherConvertToDTO(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setSurname(teacher.getSurname());
		teacherDTO.setSalary(teacher.getSalary());
		return teacherDTO;
	}

	private SubjectDTO subjectConvertToDTO(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setName(subject.getName());;
		return subjectDTO;
	}

}
