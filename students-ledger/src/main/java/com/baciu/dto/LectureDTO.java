package com.baciu.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class LectureDTO {
	
	private Long id;
	private Long classNumber;
	private LocalDateTime date;
	private TeacherDTO teacher;
	private SubjectDTO subject;
	private Set<StudentDTO> students;

}
