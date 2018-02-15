package com.baciu.dto;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class TeacherDTO {
	
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
	
	@NotNull(message = "salary may not be null")
	@Min(0)
	private Double salary;
	
	@Length.List({
	    @Length(min = 3, message = "password too short (min 3 chars)"),
	    @Length(max = 30, message = "password too long (max 30 chars)")
	})
	private String password;
	
	@Email
	@NotEmpty(message = "email may not be empty")
	private String email;
	private Set<LectureDTO> lectures;

}
