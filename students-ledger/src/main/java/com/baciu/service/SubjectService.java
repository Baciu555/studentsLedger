package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Subject;
import com.baciu.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> getAll() {
		return (List<Subject>) subjectRepository.findAll();
	}
	
	public Subject getSubject(Long id) {
		return subjectRepository.findOne(id);
	}
	
	public Subject addSubject(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	public Subject updateSubject(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	public void deleteSubject(Subject subject) {
		subjectRepository.delete(subject);
	}

}
