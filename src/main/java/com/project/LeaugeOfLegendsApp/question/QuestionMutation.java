package com.project.LeaugeOfLegendsApp.question;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.Part;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.file.Audio;
import com.project.LeaugeOfLegendsApp.file.FilesService;
import com.project.LeaugeOfLegendsApp.file.Image;
import com.project.LeaugeOfLegendsApp.file.Video;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionMutation implements GraphQLMutationResolver {

	private final QuestionService questionService;

	private final FilesService fileService;

	private final QuestionMapper questionMapper;
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Question createQuestion(QuestionDTO question, Part imageFile, Part audioFile, Part videoFile)
			throws IllegalStateException, IOException {
		
		if (!Objects.isNull(imageFile)) {
			Image image = fileService.getImageByName(imageFile.getSubmittedFileName());
			if(Objects.isNull(image)) {
				image =  fileService.getImage(fileService.addImage(imageFile));
			}
			question.setImageFile(image);
		}
		
		if (!Objects.isNull(audioFile)) {
			Audio audio = fileService.getAudioByName(audioFile.getSubmittedFileName());
			if(Objects.isNull(audio)) {
				audio = fileService.getAudio(fileService.addAudio(audioFile));
			}
			question.setAudioFile(audio);
		}
		

		if (!Objects.isNull(videoFile)) {
			Video video = fileService.getVideoByName(videoFile.getSubmittedFileName());
			if(Objects.isNull(video)) {
				video = fileService.getVideo(fileService.addVideo(videoFile));
			}
			question.setVideoFile(video);
		}
	
		return questionService.createQuestion(questionMapper.mapOfDTO(question));
	}
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public boolean createQuestionFromJsonFile(Part file) throws IllegalStateException, IOException {
		questionService.createQuestionFromJsonFile(file);
		return true;
	}
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Question updateQuestion(String id, Question questionUpdate) {
		return questionService.updateQuestion(id, questionUpdate);
	}
	
	@Secured({"ROLE_QUESTMAKER","ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public boolean deleteQuestionById(String id) {
		return questionService.deleteQuestionById(id);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public boolean deleteAllQuestions() {
		questionService.deleteAllQuestions();
		return true;
	}
}
