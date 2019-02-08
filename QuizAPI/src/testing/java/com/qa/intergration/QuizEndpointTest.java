package com.qa.intergration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.qa.business.service.QuizService;
import com.qa.rest.QuizEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class QuizEndpointTest {

	private static final String MOCK_VALUE3 = "test_value";
	
	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";


	@InjectMocks
	private QuizEndpoint endpoint;

	@Mock
	private QuizService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllQuiz() {
		Mockito.when(service.getQuiz()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getQuiz());
	}

	@Test
	public void testCreateQuiz() {
		Mockito.when(service.addQuiz(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addQuiz(MOCK_VALUE2));
		Mockito.verify(service).addQuiz(MOCK_VALUE2);
	}

	@Test
	public void testDeleteQuiz() {
		Mockito.when(service.deleteQuiz("")).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteQuiz(""));
		Mockito.verify(service).deleteQuiz("");
	}

	@Test
	public void testGetAllQuizByCat() {
		Mockito.when(service.getQuizByCat(MOCK_VALUE3)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getQuizByCat(MOCK_VALUE3));
	}
	
	
	
}