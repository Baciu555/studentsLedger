package com.baciu.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.baciu.dto.RoleDTO;
import com.baciu.entity.Role;

@Service
public class RoleConverter {

	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());

		return roleDTO;
	}

	public Set<RoleDTO> toDTO(Set<Role> roles) {
		Set<RoleDTO> rolesDTO = new HashSet<RoleDTO>();

		for (Role role : roles)
			rolesDTO.add(toDTO(role));

		return rolesDTO;
	}



}
