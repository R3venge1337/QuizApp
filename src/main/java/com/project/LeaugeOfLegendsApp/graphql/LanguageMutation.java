package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.service.LanguageService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LanguageMutation implements GraphQLMutationResolver {
	private final LanguageService languageService;
	
	public Language createLanguage(Language language) {
		return languageService.createLanguage(language);
	}
}
