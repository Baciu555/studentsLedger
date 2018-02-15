package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Subject;
import com.baciu.exception.SubjectExistsException;
import com.baciu.exception.SubjectNotExistsException;
import com.baciu.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Set<Subject> getAll() {
		return (Set<Subject>) subjectRepository.findAll();
	}
	
	public Subject getSubject(Long id) {
		if (!subjectRepository.exists(id))
			return null;
		
		return subjectRepository.findOne(id);
	}
	
	public Subject getSubjectLectures(Long id) {
		if (!subjectRepository.exists(id))
			return null;
		
		return subjectRepository.findOne(id);
	}
	
	public Subject addSubject(Subject subject) throws SubjectExistsException {
		if (subjectRepository.findByName(subject.getName()) != null)
			throw new SubjectExistsException();
		
		return subjectRepository.save(subject);
	}
	
	public Subject updateSubject(Subject subject) throws SubjectExistsException {
		if (subjectRepository.findByName(subject.getName()) != null)
			throw new SubjectExistsException();
		
		return subjectRepository.save(subject);
	}
	
	public void deleteSubject(Long id) throws SubjectNotExistsException {
		if (subjectRepository.findOne(id) == null)
			throw new SubjectNotExistsException();
		
		subjectRepository.delete(id);
	}

}
