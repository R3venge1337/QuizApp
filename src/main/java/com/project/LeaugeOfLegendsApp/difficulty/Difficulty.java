package com.project.LeaugeOfLegendsApp.difficulty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "difficulties")
@Data
@NoArgsConstructor
public class Difficulty {

	@Id
	private String id;

	private EDifficulty name;

	public Difficulty(final EDifficulty difficulty) {
		this.name = difficulty;
	}

}
