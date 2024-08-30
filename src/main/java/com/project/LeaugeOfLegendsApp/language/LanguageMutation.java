package com.project.LeaugeOfLegendsApp.language;

import graphql.kickstart.annotations.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLMutationResolver
public class LanguageMutation{
	private final LanguageService languageService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Language createLanguage(Language language) {
		return languageService.createLanguage(language);
	}
}
