package com.project.LeaugeOfLegendsApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categories")
@Data
@NoArgsConstructor
public class Category {
	
	@Id
	private String id;
	
	private ECategory name;

	public Category(ECategory category) {
		this.name = category;
	}
	
	
}
