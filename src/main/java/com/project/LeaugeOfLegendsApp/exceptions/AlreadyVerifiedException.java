package com.project.LeaugeOfLegendsApp.exceptions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AlreadyVerifiedException extends RuntimeException implements GraphQLError {
    public AlreadyVerifiedException(final String message) {
        super(message);
    }

    public AlreadyVerifiedException(final UUID uuid) {
        super(String.format("Account already has been verified: %s", uuid));
    }

    public AlreadyVerifiedException(final String message, final Object... args) {
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
