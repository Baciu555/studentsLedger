package com.baciu.dto;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class SubjectDTO {
	
	private Long id;
	
	@NotEmpty(message = "name may not empty")
	private String name;
	private Set<LectureDTO> lectures;

}
