package com.baciu.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Administrator extends User {

	private static final long serialVersionUID = 1L;
	
	@Builder
	public Administrator(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "administrator_role", joinColumns = {
			@JoinColumn(name = "administrator_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public Set<Role> roles = new HashSet<>(0);
	
	@PrePersist
	private void setDefaultRole() {
		Role role = Role.builder().id(3L).build();
		this.setRoles(new HashSet<>(Collections.singleton(role)));
	}


}
