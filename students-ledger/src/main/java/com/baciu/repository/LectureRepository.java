package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;

import com.baciu.entity.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Long> {

}
