package com.northqstandalone.maven.services;

import java.io.IOException;
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
