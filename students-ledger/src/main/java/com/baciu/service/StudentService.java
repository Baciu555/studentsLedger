package com.baciu.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baciu.entity.Role;
import com.baciu.entity.Student;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.StudentNotExistsException;
import com.baciu.repository.StudentRepository;
import com.baciu.validation.FieldValidator;

@Service
public class StudentService {
	
	private Long DEFAULT_ROLE_ID = 1L;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FieldValidator fieldValidator;
	
	public Student getStudent(Long id) {
		if (!studentRepository.exists(id))
			return null;
		
		return studentRepository.findOne(id);
	}
	
	public Student getStudentLectures(Long id) {
		if (!studentRepository.exists(id))
			return null;
		
		return studentRepository.findOne(id);
	}
	
	public Student addStudent(Student student) throws EmailExistsException {
		if (studentRepository.findByEmail(student.getEmail()) != null)
			throw new EmailExistsException();
		
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setRoles(createDefaultRole());
		
		return studentRepository.save(student);
	}
	
	private Set<Role> createDefaultRole() {
		Role role = new Role();
		role.setId(DEFAULT_ROLE_ID);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		return roles;
	}

	public Student updateStudent(Student student) throws EmailExistsException {
		Student existedStudent = studentRepository.findOne(student.getId());
		
		if (studentRepository.findByEmail(student.getEmail()) != null
				&& !student.getEmail().equals(existedStudent.getEmail()))
			throw new EmailExistsException();
		
		existedStudent.setName(student.getName());
		existedStudent.setSurname(student.getSurname());
		existedStudent.setEmail(student.getEmail());
		existedStudent.setPassword(passwordEncoder.encode(student.getPassword()));
		existedStudent.setCourse(student.getCourse());
			
		return studentRepository.save(existedStudent);
	}
	
	public void deleteStudent(Long id) throws StudentNotExistsException {
		Student student = studentRepository.findOne(id);
		if (student == null) throw new StudentNotExistsException();
		studentRepository.delete(id);
	}

	public Iterable<Student> getAll() {
		return studentRepository.findAll();
	}
	
	public Student getByEmail(String email) {
		Student student = studentRepository.findByEmail(email);
		return student == null ? null : student;
	}

}
