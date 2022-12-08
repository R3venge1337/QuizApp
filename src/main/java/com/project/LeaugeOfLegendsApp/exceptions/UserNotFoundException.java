 package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.Collections;

public class UserNotFoundException extends RuntimeException implements GraphQLError {

	
	private static final long serialVersionUID = -8444811766002409574L;
	
	
	public UserNotFoundException(String username) {
		super("User not exist " + username );
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
