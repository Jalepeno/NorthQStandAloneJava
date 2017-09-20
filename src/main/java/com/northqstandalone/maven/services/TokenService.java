package com.northqstandalone.maven.services;

import java.io.IOException;

public class TokenService {
	
	private NorthQRestfulUtils utils;
	
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}
	
	public String get() throws IOException {
		return utils.getTokenString();
	}

}
