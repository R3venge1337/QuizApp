package com.project.LeaugeOfLegendsApp.auth.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

interface AccountRepository extends MongoRepository<Account, String>, QuerydslPredicateExecutor<Account> {
    Optional<Account> findByUsername(final String username);

    Optional<Account> findByVerificationCode(final String verificationCode);

    Optional<Account> findByEmail(final String email);

    Boolean existsByUsername(final String username);

    Boolean existsByEmail(final String email);

    Optional<Account> findByUuid(final UUID uuid);

    void deleteByUuid(final UUID uuid);
}

