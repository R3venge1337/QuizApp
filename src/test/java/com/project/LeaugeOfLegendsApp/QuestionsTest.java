package com.project.LeaugeOfLegendsApp;




import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.project.LeaugeOfLegendsApp.service.QuestionService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LeaugeOfLegendsAppApplication.class)
@AutoConfigureMockMvc
public class QuestionsTest {
	
	
	
	@MockBean
	QuestionService questionServiceMock;
	
	/*
	@ParameterizedTest
	@ValueSource(ints = {10})
	public void shouldGetTenQuestions(int limit) throws IOException {
       
		//given
		int questionLength = 10;
		Question  question1 = new Question();
		Question  question2 = new Question();
		Question  question3 = new Question();
		Question  question4 = new Question();
		Question  question5 = new Question();
		Question  question6 = new Question();
		Question  question7 = new Question();
		Question  question8 = new Question();
		Question  question9 = new Question();
		Question  question10 = new Question();
		List<Question> listQuestion = List.of(question1,question2,question3,question4,question5,question6,question7,question8,question9,question10);
		
		//when
	    when(questionServiceMock.findAllQuestions(limit)).thenReturn(listQuestion);
		assertThat(listQuestion, Matchers.hasSize(questionLength));

	}
	
	*/

}
