package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.TeacherConverter;
import com.baciu.dto.TeacherDTO;
import com.baciu.entity.Teacher;
import com.baciu.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
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
		if (teacherRepository.findOne(teacher.getId()) != null)
			throw new Exception("taecher already exists");
		
		return teacherConverter.toDTO(teacherRepository.save(teacher));
	}
	
	public TeacherDTO updateTeacher(Teacher teacher) {
		return teacherConverter.toDTO(teacherRepository.save(teacher));
	}
	
	public void deleteTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}

	public Set<TeacherDTO> getAll() {
		return teacherConverter.toDTO(teacherRepository.findAll());
	}

}
