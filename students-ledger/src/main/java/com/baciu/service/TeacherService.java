package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.TeacherConverter;
import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;
import com.baciu.repository.TeacherRepository;
import com.baciu.validation.FieldValidator;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@Autowired
	private FieldValidator fieldValidator;
	
	public TeacherDTO getTeacher(Long id) {
		if (!teacherRepository.exists(id))
			return null;
		
		return teacherConverter.toDTO(teacherRepository.findOne(id));
	}
	
	public TeacherDTO getTeacherLectures(Long id) {
		if (!teacherRepository.exists(id))
			return null;
		
		return teacherConverter.toDTOLectures(teacherRepository.findOne(id));
	}
	
	public TeacherDTO addTeacher(Teacher teacher) throws Exception {
		if (!fieldValidator.validatePassword(teacher.getPassword()))
			throw new Exception("wrong password [min 3 chars, max 50 chars]");
		
		if (teacherRepository.findOne(teacher.getId()) != null)
			throw new Exception("teacher already exists");
		
		if (teacherRepository.findByEmail(teacher.getEmail()) != null)
			throw new Exception("email already exists");
		
		return teacherConverter.toDTO(teacherRepository.save(teacher));
	}
	
	public TeacherDTO updateTeacher(Teacher teacher) {
		Teacher existedTeacher = teacherRepository.findOne(teacher.getId());
		teacher.setPassword(existedTeacher.getPassword());
		
		if (teacherRepository.findByEmail(teacher.getEmail()) != null && teacher.getEmail().equals(existedTeacher.getEmail())) {
			return teacherConverter.toDTO(teacherRepository.save(teacher));
		} else if (teacherRepository.findByEmail(teacher.getEmail()) == null) {
			return teacherConverter.toDTO(teacherRepository.save(teacher));
		}
		
		return null;
	}
	
	public void deleteTeacher(Long id) {
		teacherRepository.delete(id);
	}

	public Set<TeacherDTO> getAll() {
		return teacherConverter.toDTO(teacherRepository.findAll());
	}

}
