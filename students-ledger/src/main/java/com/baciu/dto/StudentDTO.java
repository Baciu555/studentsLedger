package com.baciu.dto;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class StudentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	
	@Length.List({
	    @Length(min = 3, message = "name too short (min 3 chars)"),
	    @Length(max = 30, message = "name too long (max 30 chars)")
	})
	private String name;
	
	@Length.List({
	    @Length(min = 3, message = "surname too short (min 3 chars)"),
	    @Length(max = 30, message = "surname too long (max 30 chars)")
	})
	private String surname;
	
	@NotEmpty(message = "email may not be empty")
	@Email
	private String email;
	
	@Length.List({
	    @Length(min = 3, message = "password too short (min 3 chars)"),
	    @Length(max = 30, message = "password too long (max 30 chars)")
	})
	private String password;
	
	@NotEmpty(message = "course may not be empty")
	private String course;
	private Set<RoleDTO> roles;
	private Set<LectureDTO> lectures;

}
