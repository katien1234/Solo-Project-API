package com.qa.intergration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;
import com.qa.rest.AccountEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class AccountEndpointTest {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountEndpoint endpoint;

	@Mock
	private AccountService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(service.getAccount()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAccount());
	}

	@Test
	public void testCreateAccount() {
		Mockito.when(service.addAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addAccount(MOCK_VALUE2));
		Mockito.verify(service).addAccount(MOCK_VALUE2);
	}

	@Test
	public void testDeleteAccount() {
		Mockito.when(service.deleteAccount("")).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteAccount(""));
		Mockito.verify(service).deleteAccount("");
	}

}