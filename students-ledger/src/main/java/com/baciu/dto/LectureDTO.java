package com.baciu.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LectureDTO {
	
	private Long id;
	
	@NotNull(message = "class number may not be null")
	private Long classNumber;
	private Date date;
	private TeacherDTO teacher;
	private SubjectDTO subject;
	private Set<StudentDTO> students;

}
