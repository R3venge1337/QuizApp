package com.project.LeaugeOfLegendsApp.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class QuestionNotFoundException extends RuntimeException implements GraphQLError  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5473630341000817012L;

	public QuestionNotFoundException(String questionId) {
		super("Question cannot be found " + questionId);
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
