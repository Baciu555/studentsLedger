package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.StudentConverter;
import com.baciu.dto.StudentDTO;
import com.baciu.entity.Student;
import com.baciu.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentConverter studentConverter;
	
	public StudentDTO getStudent(Long id) {
		if (!studentRepository.exists(id))
			return null;
		
		return studentConverter.toDTO(studentRepository.findOne(id));
	}
	
	public StudentDTO getStudentLectures(Long id) {
		if (!studentRepository.exists(id))
			return null;
		
		return studentConverter.toDTOLectures(studentRepository.findOne(id));
	}
	
	public StudentDTO addStudent(Student student) throws Exception {
		if (studentRepository.findOne(student.getId()) != null)
			throw new Exception("user already exists");
		
		if (studentRepository.findByEmail(student.getEmail()) != null)
			throw new Exception("email already exists");
		
		return studentConverter.toDTO(studentRepository.save(student));
	}
	
	public StudentDTO updateStudent(Student student) {
		if (studentRepository.findByEmail(student.getEmail()) != null)
			return null;
		
		return studentConverter.toDTO(studentRepository.save(student));
	}
	
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

	public Set<StudentDTO> getAll() {
		return studentConverter.toDTO(studentRepository.findAll());
	}

}
