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
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "lectures")
@Entity
public class Teacher implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
	
	@Column(nullable = false)
	@NotNull(message = "salary may not be null")
	@Min(0)
	private Double salary;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private Set<Lecture> lectures = new HashSet<>(0);
	
}
