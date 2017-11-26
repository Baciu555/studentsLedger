package com.baciu.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SubjectDTO {
	
	private Long id;
	private String name;
	private Set<LectureDTO> lectures;

}
