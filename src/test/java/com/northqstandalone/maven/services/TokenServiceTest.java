package com.northqstandalone.maven.services;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TokenServiceTest extends TestCase {

	private NorthQRestfulUtils _utils;
	private TokenService _service;
	private CredentialsService _credentialsService;

	private ArrayList<String> credentials;

	@Before
	public void setUp() throws Exception {
		_utils = new NorthQRestfulUtils();
		_service = new TokenService();
		_credentialsService = new CredentialsService();
		
		_service.setNorthQService(_utils);
		credentials = _credentialsService.getUserCredentials();
	}

	@Test
	public void testGetTokenCorrectUser() throws Exception {

		String token = _service.get(credentials.get(0), credentials.get(1));

		// Assert that token is not null
		assertNotNull(token);
	}

	@Test
	public void testGetTokenWrongUser() {
		
		String token = null;
		
		try {
			token = _service.get("dtu7", "dtu7");
			System.out.println(token);
		} catch (Exception e) {
			// Do nothing
		}

		// Assert that token is null
		assertNull(token);
	}
}
