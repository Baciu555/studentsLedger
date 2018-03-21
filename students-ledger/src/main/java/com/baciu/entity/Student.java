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
import javax.persistence.PrePersist;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Student extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public Student(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}

	@Column(nullable = false)
	private String course;
	
	@Column(nullable = false)
	private Integer semester;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_lecture", joinColumns = {
			@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "lecture_id")})
	private Set<Lecture> lectures = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_role", joinColumns = {
			@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	protected Set<Role> roles = new HashSet<>(0);
	
	@PrePersist
	private void setDefaultRole() {
		Role role = Role.builder().id(1L).build();
		Set<Role> roles = new HashSet<>(Collections.singleton(role));
		this.setRoles(roles);
	}

}
