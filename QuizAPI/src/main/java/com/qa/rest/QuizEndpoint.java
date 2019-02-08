package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.qa.business.service.QuizService;

@Path("/quiz")
public class QuizEndpoint {


	@Inject
	private QuizService service;
	
	@Path("/getQuiz")
	@GET
	@Produces({ "application/json" })
	public String getQuiz() {
		return service.getQuiz();
	}
	
	@Path("/getQuizByCat/{id}")
	@GET
	@Produces({ "application/json" })
	public String getQuizByCat(@PathParam("id") String category) {
		return service.getQuizByCat(category);
	}
	
	@Path("/createQuiz")
	@POST
	@Produces({ "application/json" })
	public String addQuiz(String quiz) {
		return service.addQuiz(quiz);
	}
	

	@Path("/deleteQuiz/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteQuiz(@PathParam("id") String question) {
		return service.deleteQuiz(question);
	}
	
	@Path("/updateQuiz/{id}")
	@PUT
	@Produces({"application/json"})
	public String updateQuiz(@PathParam("id") String question, String quiz) {
		return service.updateQuiz(question, quiz);
	}
	
	
	public void setService(QuizService service) {
		this.service = service;
	}
	
	
}