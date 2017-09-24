package com.northqstandalone.maven.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

import com.northqstandalone.maven.NorthQ.AppTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TokenServiceTest extends TestCase {

	private NorthQRestfulUtils utils;
	private TokenService service;
	private CredentialsService _credentialsService;
	
	private ArrayList<String> credentials;

	public TokenServiceTest() {
		utils = new NorthQRestfulUtils();
		service = new TokenService();
		_credentialsService = new CredentialsService();
		
		service.setNorthQService(utils);
		
		credentials = _credentialsService.getUserCredentials();
	}
	
	public void testGetToken() {
		String token = null;

		try {
			token = service.get(credentials.get(0), credentials.get(1));
			assertNotNull(token);
		} catch (HTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assert that token is not null
		assertNotNull(token);
	}

	public void testGetTokenWrongUser() {

		String token = null;

		try {
			token = service.get("dtu7", "dtu7");
			System.out.println("Token is: " + token);
			assertNotNull(token);
		} catch (HTTPException e) {
			// Do nothing
		} catch (IOException e) {
			// Do nothing
		} catch (Exception e) {
			// Do nothing
		}

		// Assert that token is not null
		assertNull(token);
	}
}
