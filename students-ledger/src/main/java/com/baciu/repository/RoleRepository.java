package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
