package com.project.LeaugeOfLegendsApp.file;

import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
class FileController {
    private final FilesService fileService;

    @MutationMapping
    String addImage(@Argument final Part file) throws IOException {
        return fileService.addImage(file);
    }

    @MutationMapping
    String addAudio(@Argument final Part file) throws IOException {
        return fileService.addAudio(file);
    }

    @MutationMapping
    String addVideo(@Argument final Part file) throws IOException {
        return fileService.addVideo(file);
    }

    @QueryMapping
    public Image getImage(@Argument final String id) {
        return fileService.getImage(id);
    }

    @QueryMapping
    public Audio getAudio(@Argument final String id) {
        return fileService.getAudio(id);
    }

    @QueryMapping
    public Video getVideo(@Argument final String id) throws IllegalStateException, IOException {
        return fileService.getVideo(id);
    }

}
