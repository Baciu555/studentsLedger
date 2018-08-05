package com.baciu.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"teacher", "subject", "students"})
public class LectureDTO {
	
	private Long id;
	
	@NotNull(message = "class number may not be null")
	private Long classNumber;
	
	@NotNull(message = "date may not be null")
	private Date date;
	
	@NotNull(message = "subject may not be null")
	private String subject;
	private TeacherDTO teacher;
	private Set<StudentDTO> students;

}
