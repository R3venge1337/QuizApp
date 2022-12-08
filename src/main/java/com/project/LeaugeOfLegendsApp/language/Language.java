package com.project.LeaugeOfLegendsApp.language;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "languages")
@Data
@NoArgsConstructor
public class Language {
	
	@Id
	private String id;
	
	private ELanguage name;

	public Language(ELanguage language) {
		this.name = language;
	}
	
	
}
