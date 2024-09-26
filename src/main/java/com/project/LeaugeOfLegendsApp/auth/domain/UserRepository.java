package com.project.LeaugeOfLegendsApp.auth.domain;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
    Optional<User> findByUuid(final UUID uuid);

    @Query("SELECT u FROM User u INNER JOIN u.account acc WHERE acc.username = :username")
    Optional<User> findByUsername(@Param(value = "username") String username);

    void deleteByUuid(final UUID uuid);
}
