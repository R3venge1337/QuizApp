package com.project.LeaugeOfLegendsApp.question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.Part;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.LeaugeOfLegendsApp.auth.User;
import com.project.LeaugeOfLegendsApp.auth.UserRepository;
import com.project.LeaugeOfLegendsApp.category.Category;
import com.project.LeaugeOfLegendsApp.category.CategoryRepository;
import com.project.LeaugeOfLegendsApp.difficulty.Difficulty;
import com.project.LeaugeOfLegendsApp.difficulty.DifficultyRepository;
import com.project.LeaugeOfLegendsApp.exceptions.CategoryNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.DifficultyNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.LanguageNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.QuestionNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.UserNotFoundException;
import com.project.LeaugeOfLegendsApp.file.Audio;
import com.project.LeaugeOfLegendsApp.file.AudioRepository;
import com.project.LeaugeOfLegendsApp.file.Image;
import com.project.LeaugeOfLegendsApp.file.ImageRepository;
import com.project.LeaugeOfLegendsApp.file.Video;
import com.project.LeaugeOfLegendsApp.file.VideoRepository;
import com.project.LeaugeOfLegendsApp.language.Language;
import com.project.LeaugeOfLegendsApp.language.LanguageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final LanguageRepository languageRepository;
	private final CategoryRepository categoryRepository;
	private final DifficultyRepository difficultyRepository;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	private final AudioRepository audioRepository;
	private final VideoRepository videoRepository;

	public Question createQuestion(Question questionObject) {

		User user = userRepository
				.findByUsername(
						SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
				.orElseThrow(() -> new UserNotFoundException(
						SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName()));

		Language lang = languageRepository.findByName(questionObject.getLanguage().getName())
				.orElseThrow(() -> new LanguageNotFoundException(questionObject.getLanguage().getName().name()));

		Category cat = categoryRepository.getCategoryByName(questionObject.getCategory().getName())
				.orElseThrow(() -> new CategoryNotFoundException(questionObject.getCategory().getName().name()));

		Difficulty diff = difficultyRepository.findByName(questionObject.getDifficulty().getName())
				.orElseThrow(() -> new DifficultyNotFoundException(questionObject.getDifficulty().getName().name()));

		Question createQuestion = new Question(questionObject.getQuestionName(), questionObject.getAnswerA(),
				questionObject.getAnswerB(), questionObject.getAnswerC(), questionObject.getAnswerD(),
				questionObject.getCorrectAnswer(), user, Instant.now(), Instant.now(), lang, cat, diff,
				questionObject.getAudioFile(), questionObject.getImageFile(), questionObject.getVideoFile());

		return questionRepository.insert(createQuestion);
	}

	public void createQuestionFromJsonFile(Part file) throws IOException {

		if (Objects.isNull(file)) {
			throw new FileNotFoundException("File not found");
		}

		// create object mapper instance
		ObjectMapper mapper = new ObjectMapper();

		// convert JSON array to list of questions
		List<Question> questionsObject = Arrays.asList(mapper.readValue(file.getInputStream(), Question[].class));
		// questionsObject.forEach(System.out::println);

		questionsObject.stream().forEach(x -> {

			User user = userRepository
					.findByUsername(
							SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
					.orElseThrow(() -> new UserNotFoundException(SecurityContextHolder.getContextHolderStrategy()
							.getContext().getAuthentication().getName()));
			Language lang = languageRepository.findByName(x.getLanguage().getName())
					.orElseThrow(() -> new LanguageNotFoundException(x.getLanguage().getName().name()));
			Category cat = categoryRepository.getCategoryByName(x.getCategory().getName())
					.orElseThrow(() -> new CategoryNotFoundException(x.getCategory().getName().name()));
			Difficulty diff = difficultyRepository.findByName(x.getDifficulty().getName())
					.orElseThrow(() -> new DifficultyNotFoundException(x.getDifficulty().getName().name()));

			if (!Objects.isNull(x.getAudioFile())) {
				Audio audio = audioRepository.findAudioByAudioName(x.getAudioFile().getAudioName());
				x.setAudioFile(audio);
			}

			if (!Objects.isNull(x.getImageFile())) {
				Image img = imageRepository.findImageByImageName(x.getImageFile().getImageName());
				x.setImageFile(img);
			}

			if (!Objects.isNull(x.getVideoFile())) {
				Video video = videoRepository.findVideoByVideoName(x.getVideoFile().getVideoName());
				x.setVideoFile(video);
			}

			x.setAuthor(user);
			x.setLanguage(lang);
			x.setCategory(cat);
			x.setDifficulty(diff);

			questionRepository.insert(x);
		});
	}

	public Question findQuestionById(String id) {
		return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
	}

	public Question updateQuestion(String id, Question question) {

		Question updatedQuestion = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
		updatedQuestion.setQuestionName(question.getQuestionName());
		updatedQuestion.setAnswerA(question.getAnswerA());
		updatedQuestion.setAnswerB(question.getAnswerB());
		updatedQuestion.setAnswerC(question.getAnswerC());
		updatedQuestion.setAnswerD(question.getAnswerD());
		updatedQuestion.setCorrectAnswer(question.getCorrectAnswer());
		updatedQuestion.setCategory(question.getCategory());
		updatedQuestion.setLanguage(question.getLanguage());
		updatedQuestion.setDifficulty(question.getDifficulty());

		return questionRepository.save(updatedQuestion);
	}

	public List<Question> findAllQuestions(int limit) {
		return questionRepository.findAll().stream().limit(limit).toList();

	}

	public boolean deleteQuestionById(String id) {
		if (!Objects.isNull(id)) {
			Question q = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
			questionRepository.delete(q);
			return true;
		}
		return false;
	}

	public void deleteAllQuestions() {
		questionRepository.deleteAll();
	}
}
