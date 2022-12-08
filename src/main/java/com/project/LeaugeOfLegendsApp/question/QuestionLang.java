package com.project.LeaugeOfLegendsApp.question;


import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.LeaugeOfLegendsApp.auth.User;
import com.project.LeaugeOfLegendsApp.language.ELanguage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "questions_lang")
@Data
@NoArgsConstructor
public class QuestionLang {
	
	@Id
	private String id;
	
	private String questionName;
	
	private String answerA;
	
	private String asnwerB;
	
	private String answerC;
	
	private String answerD;
	
	private String correctAnswer;
	
	private User author;
	
	private Instant createdTime;
	
	private Instant updatedTime;
	
	@DBRef
	private ELanguage language;

	public QuestionLang(String questionName, String answerA, String asnwerB, String answerC, String answerD,
			String correctAnswer, User author, Instant createdTime, Instant updatedTime, ELanguage language) {
		
		this.questionName = questionName;
		this.answerA = answerA;
		this.asnwerB = asnwerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.correctAnswer = correctAnswer;
		this.author = author;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.language = language;
	}
	
	
}
