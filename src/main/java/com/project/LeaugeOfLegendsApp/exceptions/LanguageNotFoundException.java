package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.Collections;

public class LanguageNotFoundException  extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773660755572283648L;

	
	public LanguageNotFoundException(String languageName) {
	    super("Language not exist - " + languageName);
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
