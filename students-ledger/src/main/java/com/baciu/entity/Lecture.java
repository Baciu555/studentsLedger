package com.baciu.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"teacher", "subject", "students"})
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Entity
public class Lecture implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private Long classNumber;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(nullable = false)
	private String subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_lecture", joinColumns = {
			@JoinColumn(name = "lecture_id")},
			inverseJoinColumns = {@JoinColumn(name = "student_id")})
	private Set<Student> students = new HashSet<>(0);
	
	@PrePersist
	private void setDefaultDate() {
		date = new Date();
	}
	
}
