package com.project.LeaugeOfLegendsApp.graphql;


import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.LeaugeOfLegendsApp.service.FilesService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilesMutation implements GraphQLMutationResolver {
	
	private final FilesService fileService;
	
	public String addImage(String title, MultipartFile file) throws IOException {
		return fileService.addImage(title, file);
	}
	
	public String addAudio(String title, MultipartFile file) throws IOException {
		return fileService.addAudio(title, file);
	}
	
	public String addVideo(String title, MultipartFile file) throws IOException {
		return fileService.addVideo(title, file);
	}
}