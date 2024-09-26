package com.project.LeaugeOfLegendsApp;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class UserTest {

    private static final String GRAPQL_QUERY_REQUEST_PATH = "graphql-test/request/";
    private static final String GRAPQL_QUERY_RESPONSE_PATH = "graphql-test/response/";


}
