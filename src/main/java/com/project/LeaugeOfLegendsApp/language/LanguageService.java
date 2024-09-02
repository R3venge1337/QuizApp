package com.project.LeaugeOfLegendsApp.language;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {
	
	private final LanguageRepository languageRepository;
	
	public List<Language> findAllLanguages(){
		return languageRepository.findAll();
	}
	
	public Language createLanguage(final Language lang) {
		return languageRepository.insert(lang);
	}
	
	public Language findLanguageByName(final ELanguage lang) {
		return languageRepository.findByName(lang)
							.orElseThrow();
	}

}
