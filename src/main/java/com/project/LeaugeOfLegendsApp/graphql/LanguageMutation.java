package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.service.LanguageService;

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
