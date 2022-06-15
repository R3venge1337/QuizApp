package com.project.LeaugeOfLegendsApp.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Question;
import com.project.LeaugeOfLegendsApp.service.QuestionService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionQuery implements GraphQLQueryResolver {

	private final QuestionService questionService;
	
	public List<Question> findAllQuestions(int limit){
		return questionService.findAllQuestions(limit);
	}
	
	

}
