package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Teacher;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.TeacherNotExistsException;
import com.baciu.repository.TeacherRepository;
import com.baciu.validation.FieldValidator;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private FieldValidator fieldValidator;
	
	public Set<Teacher> getAll() {
		return (Set<Teacher>) teacherRepository.findAll();
	}
	
	public Teacher getTeacher(Long id) {
		if (!teacherRepository.exists(id))
			return null;
		
		return teacherRepository.findOne(id);
	}
	
	public Teacher getTeacherLectures(Long id) {
		if (!teacherRepository.exists(id))
			return null;
		
		return teacherRepository.findOne(id);
	}
	
	public Teacher addTeacher(Teacher teacher) throws EmailExistsException {
		if (teacherRepository.findByEmail(teacher.getEmail()) != null)
			throw new EmailExistsException();
		
		return teacherRepository.save(teacher);
	}
	
	public Teacher updateTeacher(Teacher teacher) throws EmailExistsException {
		Teacher existedTeacher = teacherRepository.findOne(teacher.getId());
		teacher.setPassword(existedTeacher.getPassword());
		
		if (teacherRepository.findByEmail(teacher.getEmail()) != null
				&& !teacher.getEmail().equals(existedTeacher.getEmail()))
			throw new EmailExistsException();
		
		return teacherRepository.save(teacher);
	}
	
	public void deleteTeacher(Long id) throws TeacherNotExistsException {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher == null) throw new TeacherNotExistsException();
		teacherRepository.delete(id);
	}

}
