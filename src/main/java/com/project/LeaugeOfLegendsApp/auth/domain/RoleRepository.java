package com.project.LeaugeOfLegendsApp.auth.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

interface RoleRepository extends MongoRepository<Role, String>, QuerydslPredicateExecutor<Role> {
    Optional<Role> findByName(final String name);

    Optional<Role> findByUuid(final UUID uuid);

    Set<Role> findRolesByUuidIn(final List<UUID> uuids);

    Boolean existsByName(final String name);
}
