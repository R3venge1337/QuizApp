package com.project.LeaugeOfLegendsApp.graphql;


import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.service.FilesService;

import graphql.kickstart.tools.GraphQLMutationResolver;
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