package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.Collections;

public class CategoryNotFoundException extends RuntimeException implements GraphQLError  {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2287760704153966451L;

	public CategoryNotFoundException(String categoryName) {
		super("Category cannot be found " + categoryName);
	}
	
	@Override
	public List<SourceLocation> getLocations() {
		return Collections.emptyList();
	}

	@Override
	public ErrorClassification getErrorType() {
		return ErrorType.DataFetchingException;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		return GraphQLError.super.getExtensions();
	}

}
