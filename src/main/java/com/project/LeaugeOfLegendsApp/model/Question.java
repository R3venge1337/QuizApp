package com.project.LeaugeOfLegendsApp.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "questions")
@Data
@NoArgsConstructor
public class Question {

	@Id
	private String id;

	private String questionName;

	private String answerA;

	private String answerB;

	private String answerC;

	private String answerD;

	private String correctAnswer;

	private User author;

	private Instant createdTime;

	private Instant updatedTime;

	private Language language;

	private Category category;

	private Difficulty difficulty;

	private Audio audioFile;

	private Image imageFile;

	public Question(String questionName, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, User author, Instant createdTime, Instant updatedTime, Language language,
			Category category, Difficulty difficulty, Audio audioFile, Image imageFile) {
		this.questionName = questionName;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.correctAnswer = correctAnswer;
		this.author = author;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.language = language;
		this.category = category;
		this.difficulty = difficulty;
		this.audioFile = audioFile;
		this.imageFile = imageFile;
	}

	public Question(String questionName, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, User author, Instant createdTime, Instant updatedTime, Language language,
			Category category, Difficulty difficulty) {
		this.questionName = questionName;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.correctAnswer = correctAnswer;
		this.author = author;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.language = language;
		this.category = category;
		this.difficulty = difficulty;
	}

	


}
