package com.project.LeaugeOfLegendsApp.file;


import java.io.IOException;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilesMutation implements GraphQLMutationResolver {
	
	private final FilesService fileService;
	
	public String addImage(Part file) throws IOException {
		return fileService.addImage(file);
	}
	
	public String addAudio(Part file) throws IOException {
		return fileService.addAudio(file);
	}
	
	public String addVideo(Part file) throws IOException {
		return fileService.addVideo(file);
	}
}