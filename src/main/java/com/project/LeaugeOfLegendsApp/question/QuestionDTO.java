package com.project.LeaugeOfLegendsApp.question;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
	private String id;

	private String questionName;

	private String answerA;

	private String answerB;

	private String answerC;

	private String answerD;

	private String correctAnswer;

	private Language language;
	
	private Category category;
	
	private Difficulty difficulty;
	
	private Audio audioFile;
	
	private Image imageFile;

	private Video videoFile;
}
