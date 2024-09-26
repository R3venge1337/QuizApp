package com.project.LeaugeOfLegendsApp.exceptions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotFoundException extends RuntimeException implements GraphQLError {

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final UUID uuid) {
        super(String.format("No entity with UUID: %s", uuid));
    }

    public NotFoundException(final String message, final Object... args) {
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
