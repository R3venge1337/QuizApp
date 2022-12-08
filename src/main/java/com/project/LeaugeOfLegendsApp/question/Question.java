package com.project.LeaugeOfLegendsApp.question;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.LeaugeOfLegendsApp.auth.User;
import com.project.LeaugeOfLegendsApp.category.Category;
import com.project.LeaugeOfLegendsApp.difficulty.Difficulty;
import com.project.LeaugeOfLegendsApp.file.Audio;
import com.project.LeaugeOfLegendsApp.file.Image;
import com.project.LeaugeOfLegendsApp.file.Video;
import com.project.LeaugeOfLegendsApp.language.Language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	@DBRef
	private Language language;
	@DBRef
	private Category category;
	@DBRef
	private Difficulty difficulty;
	@DBRef
	private Audio audioFile;
	@DBRef
	private Image imageFile;
	@DBRef
	private Video videoFile;
	
	@PersistenceCreator
	public Question(String questionName, String answerA, String answerB, String answerC, String answerD,
			String correctAnswer, User author, Instant createdTime, Instant updatedTime, Language language,
			Category category, Difficulty difficulty, Audio audioFile, Image imageFile, Video videoFile) {
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
		this.videoFile = videoFile;
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

	public Question(String id, String questionName, String correctAnswer, User author, Instant createdTime,
			Instant updatedTime, Language language, Category category, Difficulty difficulty, Audio audioFile,
			Image imageFile, Video videoFile) {
		this.id = id;
		this.questionName = questionName;
		this.correctAnswer = correctAnswer;
		this.author = author;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.language = language;
		this.category = category;
		this.difficulty = difficulty;
		this.audioFile = audioFile;
		this.imageFile = imageFile;
		this.videoFile = videoFile;
	}

	

	


}
