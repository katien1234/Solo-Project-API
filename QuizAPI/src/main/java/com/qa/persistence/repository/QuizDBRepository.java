package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Quiz;
import com.qa.util.JSONUtil;

public class QuizDBRepository implements QuizRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public String getQuiz() {
		Query query = manager.createQuery("Select a FROM Quiz a");
		Collection<Quiz> quizes = (Collection<Quiz>) query.getResultList();
		return util.getJSONForObject(quizes);

	}

	@Transactional(REQUIRED)
	public String getQuizByCat(String category) {
		Query query = manager.createQuery("Select a FROM Quiz a WHERE a.category LIKE '" + category + "'");
		Collection<Quiz> quizes = (Collection<Quiz>) query.getResultList();
		return util.getJSONForObject(quizes);

	}

	@Transactional(REQUIRED)
	public String createQuiz(String qui) {
		try {
			Quiz anQuiz = util.getObjectForJSON(qui, Quiz.class);
			manager.persist(anQuiz);
			return "{\"message\": \"Question has been sucessfully added\"}";
		} catch (Exception e) {
			return "{\"message\": \"Error question has not been added, please try again\"}";
		}
	}

	@Transactional(REQUIRED)
	public String deleteQuiz(String question) {
		try {
			Quiz quizInDB = findQuiz(question);
			if (quizInDB != null) {
				manager.remove(quizInDB);
			}
			return "{\"message\": \"Question sucessfully deleted\"}";
			 
		} catch (Exception e) {
			return "{\"message\": \"Error question has not been deleted, please try again\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateQuiz(String question, String quiz) {
		try {
			Quiz theQuiz = findQuiz(question);
			manager.remove(theQuiz);
			Quiz anQuiz = util.getObjectForJSON(quiz, Quiz.class);
			manager.persist(anQuiz);
			return "{\"message\": \"Question sucessfully updated\"}";
		} catch (Exception e) {
			return "{\"message\": \"Error question has not been updated, please try again\"}";
		}
	}

	private Quiz findQuiz(String question) {
		return manager.find(Quiz.class, question);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}