package com.baciu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.converter.SubjectConverter;
import com.baciu.dto.SubjectDTO;
import com.baciu.entity.Subject;
import com.baciu.exception.SubjectExistsException;
import com.baciu.exception.SubjectNotExistsException;
import com.baciu.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SubjectConverter subjectConverter;
	
	public Set<SubjectDTO> getAll() {
		return subjectConverter.toDTO(subjectRepository.findAll());
	}
	
	public SubjectDTO getSubject(Long id) {
		if (!subjectRepository.exists(id))
			return null;
		
		return subjectConverter.toDTO(subjectRepository.findOne(id));
	}
	
	public SubjectDTO getSubjectLectures(Long id) {
		if (!subjectRepository.exists(id))
			return null;
		
		return subjectConverter.toDTOLectures(subjectRepository.findOne(id));
	}
	
	public SubjectDTO addSubject(Subject subject) throws SubjectExistsException {
		if (subjectRepository.findByName(subject.getName()) != null)
			throw new SubjectExistsException();
		
		return subjectConverter.toDTO(subjectRepository.save(subject));
	}
	
	public SubjectDTO updateSubject(Subject subject) throws SubjectExistsException {
		if (subjectRepository.findByName(subject.getName()) != null)
			throw new SubjectExistsException();
		
		return subjectConverter.toDTO(subjectRepository.save(subject));
	}
	
	public void deleteSubject(Long id) throws SubjectNotExistsException {
		if (subjectRepository.findOne(id) == null)
			throw new SubjectNotExistsException();
		
		subjectRepository.delete(id);
	}

}
