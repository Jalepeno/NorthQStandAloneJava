package com.northqstandalone.maven.controllers;

import java.util.Map;

import com.google.gson.Gson;
import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
	
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}
	
	public boolean getQPlugStatus(String token) {	
		String plugStatus;
		String gatewayStatus = utils.getGatewayStatusJSON("0000003652", "2166", token);
		String status = utils.getJsonMap(gatewayStatus).get("BinarySwitches").toString();
		
		Gson gson = new Gson();
		Object[] test = gson.fromJson(status, Object[].class);
		Map<String, Object> json = utils.getJsonMap(test[0].toString());
		plugStatus = json.get("pos").toString();
		
		return plugStatus.equals("255.0");
	}
	
	public boolean getQMotionStatus(String token) {
		String motionStatus;
		String gatewayStatus = utils.getGatewayStatusJSON("0000003652", "2166", token);
		String status = utils.getJsonMap(gatewayStatus).get("BinarySensors").toString();
		
		Gson gson = new Gson();
		Object[] test = gson.fromJson(status, Object[].class);
		Map<String, Object> json = utils.getJsonMap(test[0].toString());
		motionStatus = json.get("armed").toString();
		
		return motionStatus.equals("1.0");
	}

}
