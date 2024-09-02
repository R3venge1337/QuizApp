package com.project.LeaugeOfLegendsApp.auth;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByUsername(final String username);
	  Boolean existsByUsername(final String username);
	  Boolean existsByEmail(final String email);
}
