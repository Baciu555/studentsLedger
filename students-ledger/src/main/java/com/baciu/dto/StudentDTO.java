package com.baciu.dto;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO extends UserDTO {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public StudentDTO(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}
	
	@NotEmpty(message = "course may not be empty")
	private String course;
	
	@NotEmpty(message = "semester may not be empty")
	private Integer semester;
	private Set<RoleDTO> roles;
	private Set<LectureDTO> lectures;

}
