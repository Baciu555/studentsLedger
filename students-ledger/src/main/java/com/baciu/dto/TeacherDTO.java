package com.baciu.dto;

import java.util.Set;

import lombok.Data;

@Data
public class TeacherDTO {
	
	private Long id;
	private String name;
	private String surname;
	private Double salary;
	private Set<LectureDTO> lectures;

}
