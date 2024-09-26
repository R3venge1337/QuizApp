package com.project.LeaugeOfLegendsApp;


import com.project.LeaugeOfLegendsApp.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class CategoryTest {

    private static final String GRAPQL_QUERY_REQUEST_PATH = "./src/test/resources/graphql-test/request/";
    private static final String GRAPQL_QUERY_RESPONSE_PATH = "graphql-test/response/";

    @Autowired
    private HttpGraphQlTester httpGraphQlTester;


    @Test
    public void ShouldGetCategoryByName() throws IOException {
        final File file = new File(GRAPQL_QUERY_REQUEST_PATH + "getCategoryRequest.graphql");
        final String fileContent = new String(Files.readAllBytes(Path.of(file.getPath())));
        System.out.println(fileContent);

        this.httpGraphQlTester.document(fileContent)
                .variable("name", "ITEMS")
                .execute()
                .path("getCategoryByName")
                .entity(Category.class)
                .satisfies(category -> {
                    Assertions.assertEquals("ITEMS", category.getName().name());
                });
    }
}