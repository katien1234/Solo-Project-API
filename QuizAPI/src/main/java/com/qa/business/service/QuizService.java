package com.qa.business.service;

public interface QuizService {

	String getQuiz();

	String getQuizByCat(String category);

	String addQuiz(String quiz);

	String deleteQuiz(String question);

	String updateQuiz(String question, String quiz);

}
