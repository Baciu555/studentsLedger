package com.baciu.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"lectures", "roles"})
@Entity
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@Length.List({
	    @Length(min = 3, message = "name too short (min 3 chars)"),
	    @Length(max = 30, message = "name too long (max 30 chars)")
	})
	private String name;
	
	@Column(nullable = false)
	@Length.List({
	    @Length(min = 3, message = "surname too short (min 3 chars)"),
	    @Length(max = 30, message = "surname too long (max 30 chars)")
	})
	private String surname;
	
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "email may not be empty")
	@Email
	private String email;
	
	@Column(nullable = false)
	@NotEmpty(message = "course may not be empty")
	private String course;
	
	@Length.List({
	    @Length(min = 3, message = "password too short (min 3 chars)"),
	    @Length(max = 30, message = "password too long (max 30 chars)")
	})
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_lecture", joinColumns = {
			@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "lecture_id")})
	private Set<Lecture> lectures = new HashSet<>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_role", joinColumns = {
			@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roles = new HashSet<>(0);

}
