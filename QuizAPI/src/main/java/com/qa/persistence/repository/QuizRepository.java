package com.qa.persistence.repository;

public interface QuizRepository {

	String getQuiz();

	String getQuizByCat(String category);

	String createQuiz(String quiz);

	String deleteQuiz(String question);

	String updateQuiz(String question, String quiz);

}
