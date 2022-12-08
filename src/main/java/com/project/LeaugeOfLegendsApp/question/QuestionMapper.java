package com.project.LeaugeOfLegendsApp.question;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.shared.Mapper;

@Component
public class QuestionMapper implements Mapper<QuestionDTO, Question> {

	@Override
	public QuestionDTO mapOfEntity(Question entity) {
		  
		return QuestionDTO.builder()
				.id(entity.getId())
				.questionName(entity.getQuestionName())
				.answerA(entity.getAnswerA())
				.answerB(entity.getAnswerB())
				.answerC(entity.getAnswerC())
				.answerD(entity.getAnswerD())
				.correctAnswer(entity.getCorrectAnswer())
				.language(entity.getLanguage())
				.category(entity.getCategory())
				.difficulty(entity.getDifficulty())
				.audioFile(entity.getAudioFile())
				.imageFile(entity.getImageFile())
				.videoFile(entity.getVideoFile())
				.build();
				
	}

	@Override
	public Question mapOfDTO(QuestionDTO dto) {
		
		return Question.builder()
				.id(dto.getId())
				.questionName(dto.getQuestionName())
				.answerA(dto.getAnswerA())
				.answerB(dto.getAnswerB())
				.answerC(dto.getAnswerC())
				.answerD(dto.getAnswerD())
				.correctAnswer(dto.getCorrectAnswer())
				.language(dto.getLanguage())
				.category(dto.getCategory())
				.difficulty(dto.getDifficulty())
				.audioFile(dto.getAudioFile())
				.imageFile(dto.getImageFile())
				.videoFile(dto.getVideoFile())
				.build();
	}

}
