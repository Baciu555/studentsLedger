package com.baciu.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.StudentConverter;
import com.baciu.dto.StudentDTO;
import com.baciu.entity.Role;
import com.baciu.entity.Student;
import com.baciu.repository.StudentRepository;
import com.baciu.validation.FieldValidator;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentConverter studentConverter;
	
	@Autowired
	private FieldValidator fieldValidator;
	
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
		if (!fieldValidator.validatePassword(student.getPassword()))
			throw new Exception("wrong password [min 3 chars, max 50 chars]");
		
		if (studentRepository.findOne(student.getId()) != null)
			throw new Exception("student already exists");
		
		if (studentRepository.findByEmail(student.getEmail()) != null)
			throw new Exception("email already exists");
		
		Role role = new Role();
		role.setId(1L);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		student.setRoles(roles);
		
		return studentConverter.toDTO(studentRepository.save(student));
	}

	public StudentDTO updateStudent(Student student) {
		Student existedStudent = studentRepository.findOne(student.getId());
		student.setPassword(existedStudent.getPassword());
		
		if (studentRepository.findByEmail(student.getEmail()) != null && student.getEmail().equals(existedStudent.getEmail())) {
			return studentConverter.toDTO(studentRepository.save(student));
		} else if (studentRepository.findByEmail(student.getEmail()) == null) {
			return studentConverter.toDTO(studentRepository.save(student));
		}
			
		return null;
	}
	
	public void deleteStudent(Long id) {
		studentRepository.delete(id);
	}

	public Set<StudentDTO> getAll() {
		return studentConverter.toDTO(studentRepository.findAll());
	}
	
	public StudentDTO getByEmail(String email) {
		Student student = studentRepository.findByEmail(email);
		return student == null ? null : studentConverter.toDTO(student);
	}

}
