package com.baciu.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class LectureDTO {
	
	private Long id;
	private Long classNumber;
	private Date date;
	private TeacherDTO teacher;
	private SubjectDTO subject;
	private Set<StudentDTO> students;

}
