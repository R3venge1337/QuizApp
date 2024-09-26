package com.project.LeaugeOfLegendsApp;

import com.project.LeaugeOfLegendsApp.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.io.IOException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
class LeaugeOfLegendsAppApplicationTests {

    private static final String GRAPQL_QUERY_REQUEST_PATH = "graphql-test/request/";
    private static final String GRAPQL_QUERY_RESPONSE_PATH = "graphql-test/response/";
    @Autowired
    private HttpGraphQlTester httpGraphQlTester;
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
//
//        GraphQLResponse response = graphQLTestTemplate.postForResource(GRAPQL_QUERY_REQUEST_PATH + "getAllQuestions.graphql");
//        String expectedResponseBody = read(String.format(GRAPQL_QUERY_RESPONSE_PATH + "findAllQuestions.json"));
//        System.out.println(expectedResponseBody);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertEquals(expectedResponseBody, response.getRawResponse().getBody());
    }


}
