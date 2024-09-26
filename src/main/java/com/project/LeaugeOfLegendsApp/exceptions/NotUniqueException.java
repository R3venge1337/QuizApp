package com.project.LeaugeOfLegendsApp.exceptions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class NotUniqueException extends RuntimeException implements GraphQLError {

    public NotUniqueException(final String message) {
        super(message);
    }

    public NotUniqueException(final UUID uuid) {
        super(String.format("No entity with UUID: %s", uuid));
    }

    public NotUniqueException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
