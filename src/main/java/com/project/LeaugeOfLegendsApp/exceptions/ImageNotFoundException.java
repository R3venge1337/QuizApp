package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class ImageNotFoundException  extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2991341354770693152L;
	
	
	public ImageNotFoundException(String message) {
		super(message);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		// TODO Auto-generated method stub
		return ErrorType.DataFetchingException;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		// TODO Auto-generated method stub
		return GraphQLError.super.getExtensions();
	}
}
