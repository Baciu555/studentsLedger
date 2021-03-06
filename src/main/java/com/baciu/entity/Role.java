package com.baciu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"students", "teachers"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Student> students = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Student> teachers = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Administrator> administrators = new HashSet<>(0);

}
