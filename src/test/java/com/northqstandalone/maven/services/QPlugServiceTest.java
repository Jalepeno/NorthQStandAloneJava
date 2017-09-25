package com.northqstandalone.maven.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

import org.junit.Before;
import org.junit.Test;

import com.northqstandalone.maven.controllers.StatusController;

import junit.framework.TestCase;

public class QPlugServiceTest extends TestCase {

	private NorthQRestfulUtils _utils;
	private QPlugService _service;
	private TokenService _tservice;
	private CredentialsService _credentialsService;

	private ArrayList<String> credentials;

	@Before
	public void setUp() throws Exception {
		_utils = new NorthQRestfulUtils();
		_service = new QPlugService();
		_tservice = new TokenService();
		_credentialsService = new CredentialsService();
		
		_tservice.setNorthQService(_utils);
		_service.setNorthQService(_utils);
		credentials = _credentialsService.getUserCredentials();
	}

	@Test
	public void testUpdateQPlugStatusSucces() throws HTTPException, IOException, Exception {
		String token = _tservice.get(credentials.get(0), credentials.get(1));

		assertTrue(_service.updateQplugStatus(1, token));
	}
	
}
