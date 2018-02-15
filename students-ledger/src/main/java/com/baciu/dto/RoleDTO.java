package com.baciu.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoleDTO {
	
	private Long id;
	
	@NotNull(message = "role name may not be null")
	private String name;

}
