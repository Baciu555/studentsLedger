package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;

import com.baciu.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

	Subject findByName(String name);

}
