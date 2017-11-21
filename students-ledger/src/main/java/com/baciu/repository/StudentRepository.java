package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
