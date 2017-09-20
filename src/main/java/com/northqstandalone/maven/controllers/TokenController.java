package com.northqstandalone.maven.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.services.TokenService;

public class TokenController {
	
	private TokenService service;
	
	public TokenController() {
		
	}
	
	@Autowired
	public void setTokenService(TokenService service) {
		this.service = service;
	}

	public String getToken() {
		return service.get();
	}
	
}
