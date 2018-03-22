package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Administrator;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

}
