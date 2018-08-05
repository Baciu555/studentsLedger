package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Lecture;

@Repository
public interface LectureRepository extends CrudRepository<Lecture, Long> {

}
