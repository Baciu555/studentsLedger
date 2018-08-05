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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"roles", "lectures"})
@ToString(callSuper = true, exclude = {"roles", "lectures"})
public class Student extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public Student(Long id, String name, String surname, String password, String email, String course, Integer semester) {
		super(id, name, surname, password, email);
		this.course = course;
		this.semester = semester;
	}

	@Column(nullable = false)
	private String course;
	
	@Column(nullable = false)
	private Integer semester;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
	private Set<Lecture> lectures = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_role", joinColumns = {
			@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public Set<Role> roles = new HashSet<>(0);
	
	@PrePersist
	private void setDefaultRole() {
		Role role = Role.builder().id(1L).build();
		this.setRoles(new HashSet<>(Collections.singleton(role)));
	}

}
