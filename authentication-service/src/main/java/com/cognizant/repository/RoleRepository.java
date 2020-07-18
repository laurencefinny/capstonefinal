package com.cognizant.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Role;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, Integer> {

}
