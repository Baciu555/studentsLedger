package com.baciu.dto;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"roles", "lectures"})
@ToString(callSuper = true, exclude = {"roles", "lectures"})
public class TeacherDTO extends UserDTO {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public TeacherDTO(Long id, String name, String surname, String password, String email, Double salary) {
		super(id, name, surname, password, email);
		this.salary = salary;
	}
	
	@NotNull(message = "salary may not be null")
	@Min(0)
	private Double salary;
	private Set<LectureDTO> lectures;
	private Set<RoleDTO> roles;

}
