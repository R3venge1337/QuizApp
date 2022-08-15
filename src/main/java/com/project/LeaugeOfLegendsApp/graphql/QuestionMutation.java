package com.project.LeaugeOfLegendsApp.graphql;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.dto.QuestionDTO;
import com.project.LeaugeOfLegendsApp.mapper.QuestionMapper;
import com.project.LeaugeOfLegendsApp.model.Audio;
import com.project.LeaugeOfLegendsApp.model.Image;
import com.project.LeaugeOfLegendsApp.model.Question;
import com.project.LeaugeOfLegendsApp.model.Video;
import com.project.LeaugeOfLegendsApp.service.FilesService;
import com.project.LeaugeOfLegendsApp.service.QuestionService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionMutation implements GraphQLMutationResolver {

	private final QuestionService questionService;

	private final FilesService fileService;

	private final QuestionMapper questionMapper;
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Question createQuestion(QuestionDTO question, Part audioFile, Part imageFile, Part videoFile)
			throws IllegalStateException, IOException {

		if (audioFile != null || imageFile != null || videoFile != null) {
			Audio audio = fileService.getAudio(fileService.addAudio(audioFile));
			Image image = fileService.getImage(fileService.addImage(imageFile));
			Video video = fileService.getVideo(fileService.addVideo(videoFile));
			question.setAudioFile(audio);
			question.setImageFile(image);
			question.setVideoFile(video);
		}
		return questionService.createQuestion(questionMapper.mapOfDTO(question));
	}
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public boolean createQuestionFromJsonFile(Part file) throws IllegalStateException, IOException {
		questionService.createQuestionFromJsonFile(file);
		return true;
	}
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public boolean deleteAllQuestions() {
		questionService.deleteAllQuestions();
		return true;
	}
}
