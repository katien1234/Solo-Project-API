package com.qa.persistence.repository;

public interface AccountRepository {

	String getAccount();

	String createAccount(String account);

	String deleteAccount(String email);

	String updateAccount(String email, String account);

	String verifyAccount(String account);

}
