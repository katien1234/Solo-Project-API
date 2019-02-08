package com.qa.service.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.qa.persistence.domain.Quiz;
import com.qa.persistence.repository.QuizDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class QuizDBRepositoryTest {

	@InjectMocks
	private QuizDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"question\":\"Tom Cruise has never won an Oscar\",\"answer\":\"True\",\"category\":\"Fame\"}]";

	private static final String MOCK_OBJECT = "{\"question\":\"Tom Cruise has never won an Oscar\",\"answer\":\"True\",\"category\":\"Fame\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllQuiz() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Quiz> quizs = new ArrayList<Quiz>();
		quizs.add(new Quiz("Tom Cruise has never won an Oscar", "True", "Fame"));
		Mockito.when(query.getResultList()).thenReturn(quizs);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getQuiz());
	}

	@Test
	public void testCreateQuiz() {
		String reply = repo.createQuiz(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Question has been sucessfully added\"}");
	}

	@Test
	public void testDeleteQuiz() {
		String reply = repo.deleteQuiz(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Question sucessfully deleted\"}");
	}

	@Test
	public void testUpdateQuizTry() {
		String reply = repo.updateQuiz(MOCK_DATA_ARRAY, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Question sucessfully updated\"}");

	}

	@Test
	public void testUpdateQuizCatch() {
		String reply = repo.updateQuiz(MOCK_OBJECT, MOCK_DATA_ARRAY);
		Assert.assertEquals(reply, "{\"message\": \"Error question has not been updated, please try again\"}");

	}

}
