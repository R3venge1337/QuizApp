package com.project.LeaugeOfLegendsApp;

import com.project.LeaugeOfLegendsApp.difficulty.Difficulty;
import com.project.LeaugeOfLegendsApp.language.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class DifficultyTest {


    private static final String GRAPQL_QUERY_REQUEST_PATH = "./src/test/resources/graphql-test/request/";
    private static final String GRAPQL_QUERY_RESPONSE_PATH = "graphql-test/response/";

    @Autowired
    private HttpGraphQlTester httpGraphQlTester;

    @Test
    public void ShouldGetDifficultyByName() throws IOException {

        final File file = new File(GRAPQL_QUERY_REQUEST_PATH + "getDifficultyRequest.graphql");
        final String fileContent = new String(Files.readAllBytes(Path.of(file.getPath())));

        this.httpGraphQlTester.document(fileContent)
                .variable("difficultyName", "MEDIUM")
                .execute()
                .path("getDifficultyByName")
                .entity(Difficulty.class)
                .satisfies(difficulty -> {
                    Assertions.assertEquals("MEDIUM", difficulty.getName().name());
                });
    }

}
