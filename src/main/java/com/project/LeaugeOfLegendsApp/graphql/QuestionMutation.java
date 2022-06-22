package com.project.LeaugeOfLegendsApp.graphql;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

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
	
	public Question createQuestion(Question question,Part audioFile,Part imageFile,Part videoFile) throws IllegalStateException, IOException {
		System.out.println("Audio File multipart: " +  audioFile.getSubmittedFileName());
		System.out.println("Image File multipart: " +  imageFile.getSubmittedFileName());
		System.out.println("Video File multipart: " +  videoFile.getSubmittedFileName());
		//
		//Video videoFile

		Audio audio = fileService.getAudio(fileService.addAudio(audioFile));
		Image image = fileService.getImage(fileService.addImage(imageFile));
		Video video = fileService.getVideo(fileService.addVideo(videoFile));
		question.setAudioFile(audio);
		question.setImageFile(image);
		question.setVideoFile(video);
		System.out.println("CreateQuestion: " +  question);
		return questionService.createQuestion(question);
	}
}
