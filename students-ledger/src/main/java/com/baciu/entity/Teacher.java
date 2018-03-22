package com.baciu.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class Teacher extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public Teacher(Long id, String name, String surname, String password, String email, Double salary) {
		super(id, name, surname, password, email);
		this.salary = salary;
	}
	
	@Column(nullable = false)
	private Double salary;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private Set<Lecture> lectures = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_role", joinColumns = {
			@JoinColumn(name = "teacher_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public Set<Role> roles = new HashSet<>(0);
	
	@PrePersist
	private void setDefaultRole() {
		Role role = Role.builder().id(2L).build();
		this.setRoles(new HashSet<>(Collections.singleton(role)));
	}
	
}
