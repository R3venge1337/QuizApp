package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Question;
import com.project.LeaugeOfLegendsApp.service.QuestionService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionMutation implements GraphQLMutationResolver {
	
	private final QuestionService questionService;
	
	public Question createQuestion(Question question) {
		
		return questionService.createQuestion(question);
	}
}
