package com.northqstandalone.maven.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

public class TokenService {

	private NorthQRestfulUtils utils;

	private String userId;
	private String password;

	public TokenService() {

		// Load user credentials
		getUserCredentials();
	}

	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}

	public String get() throws IOException, HTTPException, Exception {
		return utils.getTokenString(userId, password);
	}

	public void getUserCredentials() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\file.txt"));
			userId = br.readLine();
			password = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not load credentials, create file.txt with username and password under C:\\");
		} catch (Exception e) {
			System.out.println("Problem occurred with loading credentials");
		}
	}
}
