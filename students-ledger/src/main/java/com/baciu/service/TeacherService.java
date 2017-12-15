package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.TeacherConverter;
import com.baciu.dto.TeacherDTO;
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
	private TeacherConverter teacherConverter;
	
	@Autowired
	private FieldValidator fieldValidator;
	
	public Set<TeacherDTO> getAll() {
		return teacherConverter.toDTO(teacherRepository.findAll());
	}
	
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
	
	public TeacherDTO addTeacher(Teacher teacher) throws EmailExistsException {
		if (teacherRepository.findByEmail(teacher.getEmail()) != null)
			throw new EmailExistsException();
		
		return teacherConverter.toDTO(teacherRepository.save(teacher));
	}
	
	public TeacherDTO updateTeacher(Teacher teacher) throws EmailExistsException {
		Teacher existedTeacher = teacherRepository.findOne(teacher.getId());
		teacher.setPassword(existedTeacher.getPassword());
		
		if (teacherRepository.findByEmail(teacher.getEmail()) != null
				&& !teacher.getEmail().equals(existedTeacher.getEmail()))
			throw new EmailExistsException();
		
		return teacherConverter.toDTO(teacherRepository.save(teacher));
	}
	
	public void deleteTeacher(Long id) throws TeacherNotExistsException {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher == null) throw new TeacherNotExistsException();
		teacherRepository.delete(id);
	}

}
