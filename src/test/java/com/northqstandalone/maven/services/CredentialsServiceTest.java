package com.northqstandalone.maven.services;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CredentialsServiceTest {
	
	private CredentialsService _credentialsService;
	private ArrayList<String> credentials;

	@Before
	public void setUp() throws Exception {
		_credentialsService = new CredentialsService();
		credentials = _credentialsService.getUserCredentials();
	}

	@Test
	public void testCredentialsNotNull() {

		String userId = credentials.get(0);
		String password = credentials.get(1);

		// Assert userId & password
		assertNotNull(userId);
		assertNotNull(password);
	}

	@Test
	public void testCredentialsEqual() {

		String userId = credentials.get(0);
		String password = credentials.get(1);
		
		// Assert userId & password equality
		assertEquals("dtu3", userId);
		assertEquals("dtu3", password);
	}
	
	@Test
	public void testCredentialsNotEqual() {

		String userId = credentials.get(0);
		String password = credentials.get(1);

		// Assert userId & password equality
		assertNotEquals("dtu4", userId);
		assertNotEquals("dtu4", password);
	}
	
}


