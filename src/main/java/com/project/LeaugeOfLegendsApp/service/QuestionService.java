package com.project.LeaugeOfLegendsApp.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.Category;
import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.model.Language;
import com.project.LeaugeOfLegendsApp.model.Question;
import com.project.LeaugeOfLegendsApp.model.User;
import com.project.LeaugeOfLegendsApp.repository.CategoryRepository;
import com.project.LeaugeOfLegendsApp.repository.DifficultyRepository;
import com.project.LeaugeOfLegendsApp.repository.LanguageRepository;
import com.project.LeaugeOfLegendsApp.repository.QuestionRepository;
import com.project.LeaugeOfLegendsApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final LanguageRepository languageRepository;
	private final CategoryRepository categoryRepository;
	private final DifficultyRepository difficultyRepository;
	private final UserRepository userRepository;
	
	public Question createQuestion(Question questionObject) {
		
			//System.out.println(SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication());
		User user = userRepository.findByUsername(SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
				.orElseThrow();
		Language lang = languageRepository.findByName(questionObject.getLanguage().getName()).orElseThrow();
		Category cat = categoryRepository.getCategoryByName(questionObject.getCategory().getName()).orElseThrow();
		Difficulty diff = difficultyRepository.findByName(questionObject.getDifficulty().getName()).orElseThrow();
		//System.out.println("User createQuestion: " + user);
		
		Question createQuestion = new Question(questionObject.getQuestionName(), questionObject.getAnswerA(),
				questionObject.getAnswerB(), questionObject.getAnswerC(), questionObject.getAnswerD(),
				questionObject.getCorrectAnswer(), user, Instant.now(), Instant.now(), lang,
				cat,diff);
		
		System.out.println("Question createQuestion: " + createQuestion);

		
		return questionRepository.insert(createQuestion);
	}

	public List<Question> findAllQuestions(int limit) {
		return questionRepository.findAll().stream().limit(limit).collect(Collectors.toList());

	}

}
