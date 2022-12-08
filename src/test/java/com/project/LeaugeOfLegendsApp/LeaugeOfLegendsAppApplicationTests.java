package com.project.LeaugeOfLegendsApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.project.LeaugeOfLegendsApp.category.CategoryService;

import io.micrometer.core.instrument.util.IOUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
class LeaugeOfLegendsAppApplicationTests {

	private static final String GRAPQL_QUERY_REQUEST_PATH = "request/";
	private static final String GRAPQL_QUERY_RESPONSE_PATH = "response/";

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@MockBean
	CategoryService categoryServiceMock;


	@Test
	void contextLoads() {
	}
	
	
/*
	@Test
	public void ShouldGetQuestionsWithLimitNumber() throws IOException {
		
		//given
		ECategory ecategory = ECategory.ITEMS;
		Category cat = new Category(ecategory);
		//when
		when(categoryServiceMock.getCategoryByName(ECategory.ITEMS)).thenReturn(cat);
		//then
		assertEquals(cat,new Category(ECategory.ITEMS));

		
		
		categoryServiceMock.getCategoryByName(ECategory.ITEMS);
		GraphQLResponse response = graphQLTestTemplate.postForResource(GRAPQL_QUERY_REQUEST_PATH+"getCategoryRequest.graphql");
		String expectedResponseBody = read(String.format(GRAPQL_QUERY_RESPONSE_PATH+"getCategoryResponse.json"));
		System.out.println(expectedResponseBody);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(expectedResponseBody, response.getRawResponse().getBody());
	
	}
*/
	
	@Test
	public void ShouldGetTenQuestions() throws IOException {
		
		GraphQLResponse response = graphQLTestTemplate.postForResource(GRAPQL_QUERY_REQUEST_PATH+"getAllQuestions.graphql");
		String expectedResponseBody = read(String.format(GRAPQL_QUERY_RESPONSE_PATH+"findAllQuestions.json"));
		System.out.println(expectedResponseBody);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(expectedResponseBody, response.getRawResponse().getBody());
	}

	private String read(String location) throws IOException {
		return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
	}

}
