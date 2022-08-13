package com.project.LeaugeOfLegendsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.graphql.spring.boot.test.GraphQLTestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class UserTest {
	
	private static final String GRAPQL_QUERY_REQUEST_PATH = "request/";
	private static final String GRAPQL_QUERY_RESPONSE_PATH = "response/";
	
	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

}
