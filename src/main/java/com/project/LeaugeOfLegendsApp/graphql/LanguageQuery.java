package com.project.LeaugeOfLegendsApp.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.ELanguage;
import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.service.LanguageService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LanguageQuery implements GraphQLQueryResolver{
	
	private final LanguageService languageService;
	
	public List<Language> findAllLanguages() {
		return languageService.findAllLanguages();
	}
	
	public Language findLanguageByName(ELanguage lang) {
		return languageService.findLanguageByName(lang);
	}
	
}
