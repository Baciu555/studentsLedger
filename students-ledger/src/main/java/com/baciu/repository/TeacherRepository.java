package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
	
	public Teacher findByEmail(String email);

}
