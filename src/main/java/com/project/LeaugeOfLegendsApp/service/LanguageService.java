package com.project.LeaugeOfLegendsApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.ELanguage;
import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.repository.LanguageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {
	
	private final LanguageRepository languageRepository;
	
	public List<Language> findAllLanguages(){
		return languageRepository.findAll();
	}
	
	public Language createLanguage(Language lang) {
		return languageRepository.insert(lang);
	}
	
	public Language findLanguageByName(ELanguage lang) {
		return languageRepository.findByName(lang)
							.orElseThrow();
	}

}
