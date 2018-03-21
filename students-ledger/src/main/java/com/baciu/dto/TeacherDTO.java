package com.baciu.dto;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDTO extends UserDTO {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public TeacherDTO(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}
	
	@NotNull(message = "salary may not be null")
	@Min(0)
	private Double salary;
	private Set<LectureDTO> lectures;
	private Set<RoleDTO> roles;

}
