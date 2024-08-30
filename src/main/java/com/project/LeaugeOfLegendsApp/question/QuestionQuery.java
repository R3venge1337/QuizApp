package com.project.LeaugeOfLegendsApp.question;

import java.util.List;

import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLQueryResolver
public class QuestionQuery {

	private final QuestionService questionService;
	
	public List<Question> findAllQuestions(int limit){
		return questionService.findAllQuestions(limit);
	}
	
	public Question findQuestionById(String id) {
		return questionService.findQuestionById(id);
	}
	
}
