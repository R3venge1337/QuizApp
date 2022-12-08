package com.project.LeaugeOfLegendsApp.language;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LanguageMutation implements GraphQLMutationResolver {
	private final LanguageService languageService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Language createLanguage(Language language) {
		return languageService.createLanguage(language);
	}
}
