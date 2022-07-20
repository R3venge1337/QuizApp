package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class LanguageNotFoundException  extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	public LanguageNotFoundException(String message) {
	    super(message);
	  }
	
	private static final long serialVersionUID = 6773660755572283648L;

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
