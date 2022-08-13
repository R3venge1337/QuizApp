package com.project.LeaugeOfLegendsApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import io.micrometer.core.instrument.util.IOUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class LanguageTest {
	
	private static final String GRAPQL_QUERY_REQUEST_PATH = "request/";
	private static final String GRAPQL_QUERY_RESPONSE_PATH = "response/";

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;
	
	private String read(String location) throws IOException {
		return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
	}
	
	@Test
	public void ShouldGetLanguageByName() throws IOException {
		
		GraphQLResponse response = graphQLTestTemplate.postForResource(GRAPQL_QUERY_REQUEST_PATH+"getLanguageRequest.graphql");
		String expectedResponseBody = read(String.format(GRAPQL_QUERY_RESPONSE_PATH+"getLanguagePLResponse.json"));
		System.out.println(expectedResponseBody);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(expectedResponseBody, response.getRawResponse().getBody());
	}
}
