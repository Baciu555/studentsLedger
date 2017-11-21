package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Student;
import com.baciu.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student getStudent(Long id) {
		return studentRepository.findOne(id);
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

	public List<Student> getAll() {
		return (List<Student>) studentRepository.findAll();
	}

}
