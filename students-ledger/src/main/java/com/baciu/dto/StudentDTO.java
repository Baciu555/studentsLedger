package com.baciu.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"roles", "lectures"})
@ToString(callSuper = true, exclude = {"roles", "lectures"})
public class StudentDTO extends UserDTO {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public StudentDTO(Long id, String name, String surname, String password, String email, String course, Integer semester) {
		super(id, name, surname, password, email);
		this.course = course;
		this.semester = semester;
	}
	
	@NotEmpty(message = "course may not be empty")
	private String course;
	
	@NotNull(message = "semester may not be null")
	private Integer semester;
	private Set<RoleDTO> roles;
	private Set<LectureDTO> lectures;

}
