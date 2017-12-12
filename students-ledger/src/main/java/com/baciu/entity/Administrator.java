package com.baciu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Administrator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Email
	@NotEmpty
	private String email;
	
	@Column(nullable = false)
	@Length(min = 5, message = "password too short (min 5 chars)")
	private String password;

}
