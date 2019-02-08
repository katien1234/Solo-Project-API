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
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {

	@InjectMocks
	private AccountDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"email\":\"katie.n1234@gmail.com\",\"password\":\"Cake\"}]";

	private static final String MOCK_OBJECT = "{\"email\":\"katie.n1234@gmail.com\",\"username\":\"katien123\",\"password\":\"Cake\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("katie.n1234@gmail.com", "katien123", "Cake"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAccount());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Account has been sucessfully added\"}");
	}

	@Test
	public void testDeleteAccount() {
		String reply = repo.deleteAccount("");
		Assert.assertEquals(reply, "{\"message\": \"Account sucessfully deleted\"}");
	}
	
	@Test
	public void testUpdateAccountTry() {
		String reply = repo.updateAccount(MOCK_DATA_ARRAY, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Account sucessfully updated\"}");

	}
	
	@Test
	public void testUpdateAccountCatch() {
		String reply = repo.updateAccount(MOCK_OBJECT, MOCK_DATA_ARRAY);
		Assert.assertEquals(reply, "{\"message\": \"Account not successfully updated\"}");

	}
	
}
