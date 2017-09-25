package com.northqstandalone.maven.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.junit.Before;
import org.junit.Test;

import com.northqstandalone.maven.controllers.StatusController;

import junit.framework.TestCase;

public class QMotionServiceTest extends TestCase {

	private NorthQRestfulUtils _utils;
	private QMotionService _service;
	private TokenService _tservice;
	private CredentialsService _credentialsService;

	private ArrayList<String> credentials;

	@Before
	public void setUp() throws Exception {
		_utils = new NorthQRestfulUtils();
		_service = new QMotionService();
		_tservice = new TokenService();
		_credentialsService = new CredentialsService();
		
		_tservice.setNorthQService(_utils);
		_service.setNorthQService(_utils);
		credentials = _credentialsService.getUserCredentials();
		
	}

	@Test
	public void testArmQMotion() throws HTTPException, IOException, Exception {
		String token = _tservice.get(credentials.get(0), credentials.get(1));
		Response response = _service.armMotion(token);
		
		assertTrue(response.getStatus() == 200);
		
	}

	@Test
	public void testDisarmQMotion() throws HTTPException, IOException, Exception {
		String token = _tservice.get(credentials.get(0), credentials.get(1));
		Response response = _service.disarmMotion(token);
		
		assertTrue(response.getStatus() == 200);
	}
	
	@Test
	public void testArmMotionTokenWrong() throws HTTPException, IOException, Exception {
		Response response = _service.armMotion("djiadijdadada");
		
		assertTrue(response.getStatus() == 403);
	}
}
