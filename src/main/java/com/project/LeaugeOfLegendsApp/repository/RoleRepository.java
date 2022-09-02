package com.project.LeaugeOfLegendsApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.ERole;
import com.project.LeaugeOfLegendsApp.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>  {
	Optional<Role> findByName(ERole name);
}