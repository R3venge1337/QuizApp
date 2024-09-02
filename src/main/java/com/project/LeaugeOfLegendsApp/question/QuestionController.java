package com.project.LeaugeOfLegendsApp.question;

import com.project.LeaugeOfLegendsApp.file.Audio;
import com.project.LeaugeOfLegendsApp.file.FilesService;
import com.project.LeaugeOfLegendsApp.file.Image;
import com.project.LeaugeOfLegendsApp.file.Video;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
class QuestionController {

    private final QuestionService questionService;

    private final FilesService fileService;

    private final QuestionMapper questionMapper;

    @Secured({"ROLE_QUESTMAKER", "ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Question createQuestion(@Argument final QuestionDTO question, @Argument final Part imageFile, @Argument final Part audioFile, @Argument final Part videoFile)
            throws IllegalStateException, IOException {

        if (!Objects.isNull(imageFile)) {
            Image image = fileService.getImageByName(imageFile.getSubmittedFileName());
            if (Objects.isNull(image)) {
                image = fileService.getImage(fileService.addImage(imageFile));
            }
            question.setImageFile(image);
        }

        if (!Objects.isNull(audioFile)) {
            Audio audio = fileService.getAudioByName(audioFile.getSubmittedFileName());
            if (Objects.isNull(audio)) {
                audio = fileService.getAudio(fileService.addAudio(audioFile));
            }
            question.setAudioFile(audio);
        }


        if (!Objects.isNull(videoFile)) {
            Video video = fileService.getVideoByName(videoFile.getSubmittedFileName());
            if (Objects.isNull(video)) {
                video = fileService.getVideo(fileService.addVideo(videoFile));
            }
            question.setVideoFile(video);
        }

        return questionService.createQuestion(questionMapper.mapOfDTO(question));
    }

    @Secured({"ROLE_QUESTMAKER", "ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    boolean createQuestionFromJsonFile(@Argument final Part file) throws IllegalStateException, IOException {
        questionService.createQuestionFromJsonFile(file);
        return true;
    }

    @Secured({"ROLE_QUESTMAKER", "ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Question updateQuestion(@Argument final String id, @Argument final Question questionUpdate) {
        return questionService.updateQuestion(id, questionUpdate);
    }

    @Secured({"ROLE_QUESTMAKER", "ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    boolean deleteQuestionById(@Argument final String id) {
        return questionService.deleteQuestionById(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    boolean deleteAllQuestions() {
        questionService.deleteAllQuestions();
        return true;
    }

    @QueryMapping
    List<Question> findAllQuestions(@Argument int limit) {
        return questionService.findAllQuestions(limit);
    }

    @QueryMapping
    Question findQuestionById(@Argument final String id) {
        return questionService.findQuestionById(id);
    }
}
