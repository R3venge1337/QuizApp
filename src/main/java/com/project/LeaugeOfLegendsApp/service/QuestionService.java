package com.project.LeaugeOfLegendsApp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.LeaugeOfLegendsApp.exceptions.CategoryNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.DifficultyNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.LanguageNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.UserNotFoundException;
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

		User user = userRepository
				.findByUsername(
						SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
		Language lang = languageRepository.findByName(questionObject.getLanguage().getName()).orElseThrow(
				() -> new LanguageNotFoundException("Language not exist - " + questionObject.getLanguage().getName()));
		Category cat = categoryRepository.getCategoryByName(questionObject.getCategory().getName()).orElseThrow(
				() -> new CategoryNotFoundException("Category not exist - " + questionObject.getCategory().getName()));
		Difficulty diff = difficultyRepository.findByName(questionObject.getDifficulty().getName())
				.orElseThrow(() -> new DifficultyNotFoundException(
						"Difficulty not exist - " + questionObject.getDifficulty().getName()));

		Question createQuestion = new Question(questionObject.getQuestionName(), questionObject.getAnswerA(),
				questionObject.getAnswerB(), questionObject.getAnswerC(), questionObject.getAnswerD(),
				questionObject.getCorrectAnswer(), user, Instant.now(), Instant.now(), lang, cat, diff,
				questionObject.getAudioFile(), questionObject.getImageFile(), questionObject.getVideoFile());

		return questionRepository.insert(createQuestion);
	}

	public List<Question> findAllQuestions(int limit) {
		return questionRepository.findAll().stream().limit(limit).collect(Collectors.toList());

	}

	public void createQuestionFromJsonFile(Part file) throws StreamReadException, DatabindException, IOException {

		if (Objects.isNull(file)) {
			throw new FileNotFoundException("File not found");
		}

		// create object mapper instance
		ObjectMapper mapper = new ObjectMapper();

		// convert JSON array to list of questions
		List<Question> questionsObject = Arrays.asList(mapper.readValue(file.getInputStream(), Question[].class));
		
		 questionsObject.stream().forEach(x -> {
			 
				User user = userRepository
						.findByUsername(
								SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
						.orElseThrow(() -> new UserNotFoundException("User not exist"));
				Language lang = languageRepository.findByName(x.getLanguage().getName()).orElseThrow(
						() -> new LanguageNotFoundException("Language not exist - " + x.getLanguage().getName()));
				Category cat = categoryRepository.getCategoryByName(x.getCategory().getName()).orElseThrow(
						() -> new CategoryNotFoundException("Category not exist - " + x.getCategory().getName()));
				Difficulty diff = difficultyRepository.findByName(x.getDifficulty().getName())
						.orElseThrow(() -> new DifficultyNotFoundException(
								"Difficulty not exist - " + x.getDifficulty().getName()));
				x.setAuthor(user);
				x.setLanguage(lang);
				x.setCategory(cat);
				x.setDifficulty(diff);
				questionRepository.insert(x);
		 });
		
	

		// print books
		//questions.forEach(System.out::println);

		
	}
}
