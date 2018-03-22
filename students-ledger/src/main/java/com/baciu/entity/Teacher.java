package com.baciu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	public Teacher(Long id, String name, String surname, String password, String email) {
		super(id, name, surname, password, email);
	}
	
	@Column(nullable = false)
	private Double salary;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private Set<Lecture> lectures = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_role", joinColumns = {
			@JoinColumn(name = "teacher_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	protected Set<Role> roles = new HashSet<>(0);
	
}
