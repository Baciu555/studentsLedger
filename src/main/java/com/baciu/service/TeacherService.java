package com.baciu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baciu.entity.Teacher;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.TeacherNotExistsException;
import com.baciu.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Iterable<Teacher> getAll() {
		return teacherRepository.findAll();
	}
	
	public Teacher getTeacher(Long id) {
		if (!teacherRepository.exists(id))
			return null;
		
		return teacherRepository.findOne(id);
	}
	
	public Teacher addTeacher(Teacher teacher) throws EmailExistsException {
		if (teacherRepository.findByEmail(teacher.getEmail()) != null)
			throw new EmailExistsException();
		
		teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
		
		return teacherRepository.save(teacher);
	}
	
	public Teacher updateTeacher(Teacher teacher) throws EmailExistsException {
		Teacher existedTeacher = teacherRepository.findOne(teacher.getId());
		
		if (teacherRepository.findByEmail(teacher.getEmail()) != null
				&& !teacher.getEmail().equals(existedTeacher.getEmail()))
			throw new EmailExistsException();
		
		existedTeacher.setName(teacher.getName());
		existedTeacher.setSurname(teacher.getSurname());
		existedTeacher.setEmail(teacher.getEmail());
		existedTeacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
		existedTeacher.setSalary(teacher.getSalary());
		
		return teacherRepository.save(existedTeacher);
	}
	
	public void deleteTeacher(Long id) throws TeacherNotExistsException {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher == null) throw new TeacherNotExistsException();
		teacherRepository.delete(id);
	}

}
