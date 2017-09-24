package com.northqstandalone.maven.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.models.ErrorModel;
import com.northqstandalone.maven.services.CredentialsService;
import com.northqstandalone.maven.services.TokenService;

public class TokenController {
	
	private TokenService service;
	private CredentialsService _credentialsService;
	private ErrorModel error;
	
	private ArrayList<String> credentials;
	
	public TokenController() {
		// Get credentials
		credentials = _credentialsService.getUserCredentials();
	}
	
	@Autowired
	public void setTokenService(TokenService service) {
		this.service = service;
	}
	
	@Autowired
	public void setErrorModel(ErrorModel error) {
		this.error = error;
	}
	
	@Autowired
	public void setCredentialsService(CredentialsService credentialsService) {
		this._credentialsService = credentialsService;
	}

	public String getToken() {
		
		try {
			return service.get(credentials.get(0), credentials.get(0));
		}
		catch (IOException e1) {
			error.setErrorMessage("An error has occurred reading file");
		}
		catch (HTTPException e2) {
			error.setErrorMessage("An error has occurred with the connection");
		}
		catch (Exception e3) {
			error.setErrorMessage("An error has occurred");
		}
		return null;
	}
	
	
	
}
