package com.qa.business.service;

import javax.inject.Inject;
import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository repo;

	public String getAccount() {
		return repo.getAccount();
	}

	public String addAccount(String account) {
		return repo.createAccount(account);

	}

	public String deleteAccount(String email) {
		return repo.deleteAccount(email);
	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}

	public String updateAccount(String email, String account) {

		return repo.updateAccount(email, account);

	}

	public String verifyAccount(String account) {
		return repo.verifyAccount(account);
	}

}
