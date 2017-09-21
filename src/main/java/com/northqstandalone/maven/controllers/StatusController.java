package com.northqstandalone.maven.controllers;

import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
	
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}
	
	public boolean getQPlugStatus(String token) {
		
		String gatewayStatus = utils.getGatewayStatusJSON("0000003652", "2166", token);
		String status = utils.getJsonMap(gatewayStatus).get("BinarySwitches").toString();
		
		return status.contains("pos=255.0") ? true : false;
	}

}
