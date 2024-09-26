package com.project.LeaugeOfLegendsApp.auth.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;


interface PermissionRepository extends MongoRepository<Permission, String>, QuerydslPredicateExecutor<Permission> {

    Optional<Permission> findByUuid(final UUID uuid);

    void deleteByUuid(final UUID uuid);

    Boolean existsByName(final String name);
}
