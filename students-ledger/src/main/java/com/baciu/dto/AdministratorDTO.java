package com.baciu.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministratorDTO extends UserDTO {

	private static final long serialVersionUID = 1L;
	
	@Builder
	public AdministratorDTO(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}
	
	private Set<RoleDTO> roles;

}
