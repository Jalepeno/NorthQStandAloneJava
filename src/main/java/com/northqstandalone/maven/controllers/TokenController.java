package com.northqstandalone.maven.controllers;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.models.ErrorModel;
import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.services.TokenService;
import com.northqstandalone.maven.view.View;

public class TokenController {
	
	private TokenService service;
	private ErrorModel error;
	
	@Autowired
	public void setTokenService(TokenService service) {
		this.service = service;
	}
	
	@Autowired
	public void setErrorModel(ErrorModel error) {
		this.error = error;
	}

	public String getToken() {
		
		try {
			return service.get();
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
