package com.northqstandalone.maven.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

public class TokenService {

	private NorthQRestfulUtils utils;

	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}

	public String get(String userId, String password) throws IOException, HTTPException, Exception {
		return utils.getTokenString(userId, password);
	}
}
