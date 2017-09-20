package com.northqstandalone.maven.services;

public class TokenService {
	
	private NorthQRestfulUtils utils;
	
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}
	
	public String get() {
		return utils.getTokenString();
	}

}
