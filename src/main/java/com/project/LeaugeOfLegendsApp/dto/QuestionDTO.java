package com.project.LeaugeOfLegendsApp.dto;

import com.project.LeaugeOfLegendsApp.model.Audio;
import com.project.LeaugeOfLegendsApp.model.Category;
import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.model.Image;
import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.model.Video;

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
