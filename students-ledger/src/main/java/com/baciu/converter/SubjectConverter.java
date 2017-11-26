package com.baciu.converter;

import com.baciu.dto.SubjectDTO;
import com.baciu.entity.Subject;

public class SubjectConverter {
	
	public SubjectDTO toDTO(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setName(subject.getName());;
		return subjectDTO;
	}

}
