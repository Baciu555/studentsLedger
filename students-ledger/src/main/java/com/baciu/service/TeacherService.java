package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Teacher;
import com.baciu.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public Teacher getTeacher(Long id) {
		return teacherRepository.findOne(id);
	}
	
	public Teacher addTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public void deteleTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}

	public List<Teacher> getAll() {
		return (List<Teacher>) teacherRepository.findAll();
	}

}
