package com.qa.business.service;

import javax.inject.Inject;
import com.qa.persistence.domain.Quiz;
import com.qa.persistence.repository.QuizRepository;
import com.qa.util.Constants;
import com.qa.util.JSONUtil;

public class QuizServiceImpl implements QuizService {

	@Inject
	private QuizRepository repo;

	@Inject

	private JSONUtil util;

	public String getQuiz() {
		return repo.getQuiz();
	}

	public String getQuizByCat(String category) {
		return repo.getQuizByCat(category);
	}

	public String addQuiz(String quiz) {
		try {
			Quiz anQuiz = util.getObjectForJSON(quiz, Quiz.class);
			String quizName = anQuiz.getQuestion();
			String checkAnswer = anQuiz.getAnswer();
			if (checkTrueOrFalse(checkAnswer) && checkSwearWords(quizName)) {
				return repo.createQuiz(quiz);
			} else if (checkTrueOrFalse(checkAnswer) && !checkSwearWords(quizName)) {
				return "{\"message\": \"No swear words please\"}";

			} else if (!checkTrueOrFalse(checkAnswer) && checkSwearWords(quizName)) {
				return "{\"message\": \"True or False answer\"}";
			} else {
				return "{\"message\": \"Answer should be true or false and no swear words\"}";
			}
		} catch (Exception e) {
			return "{\"message\": " + e.toString() + "}";
		}

	}

	public String deleteQuiz(String question) {
		return repo.deleteQuiz(question);
	}

	public void setRepo(QuizRepository repo) {
		this.repo = repo;
	}

	public String updateQuiz(String question, String quiz) {
		try {
			Quiz anQuiz = util.getObjectForJSON(quiz, Quiz.class);
			String quizName = anQuiz.getQuestion();
			String checkAnswer = anQuiz.getAnswer();
			if (checkTrueOrFalse(checkAnswer) && checkSwearWords(quizName)) {
				return repo.updateQuiz(question, quiz);
			} else if (checkTrueOrFalse(checkAnswer) && !checkSwearWords(quizName)) {
				return "{\"message\": \"No swear words please\"}";

			} else if (!checkTrueOrFalse(checkAnswer) && checkSwearWords(quizName)) {
				return "{\"message\": \"True or False answer\"}";
			} else {
				return "{\"message\": \"Answer should be true or false and no swear words\"}";
			}
		} catch (Exception e) {
			return "{\"message\": " + e.toString() + "}";
		}
	}

	// No swear words = return true
	public boolean checkSwearWords(String quizName) {
		String toLower = quizName.toLowerCase();
		String[] question = toLower.split(" ");
		for (int i = 0; i < question.length; i++) {
			for (int j = 0; j < Constants.SWEARWORDS.length; j++) {
				if (question[i].equals(Constants.SWEARWORDS[j])) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkTrueOrFalse(String answer) {
		String toLower = answer.toLowerCase();
		if (toLower.equals("true") || toLower.equals("false")) {
			return true;
		}
		return false;
	}
}
