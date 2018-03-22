package com.baciu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baciu.entity.Student;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.StudentNotExistsException;
import com.baciu.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Student getStudent(Long id) {
		if (!studentRepository.exists(id))
			return null;
		
		return studentRepository.findOne(id);
	}
	
	public Student addStudent(Student student) throws EmailExistsException {
		if (studentRepository.findByEmail(student.getEmail()) != null)
			throw new EmailExistsException();
		
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		
		return studentRepository.save(student);
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
		existedStudent.setSemester(student.getSemester());
			
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
