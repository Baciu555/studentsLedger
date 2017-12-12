package com.baciu.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class StudentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String course;
	private Set<RoleDTO> roles;
	private Set<LectureDTO> lectures;

}
