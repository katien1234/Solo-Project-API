package com.qa.business.service;

public interface AccountService {

	String getAccount();

	String addAccount(String account);

	String deleteAccount(String email);

	String updateAccount(String email, String account);

	String verifyAccount(String account);

}
