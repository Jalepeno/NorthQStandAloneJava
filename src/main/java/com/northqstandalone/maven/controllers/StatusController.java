package com.northqstandalone.maven.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.northqstandalone.maven.models.ErrorModel;
import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
	private ErrorModel error;
	
	@Autowired
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}
	
	@Autowired
	public void setErrorModel(ErrorModel error) {
		this.error = error;
	}
	
	public boolean getQPlugStatus(String token) {		
		// TODO Figure out what to do with this method
		
		try {
			String plugStatus;
			String gatewayStatus = utils.getGatewayStatusJSON("0000003652", "2166", token);
			String status = utils.getJsonMap(gatewayStatus).get("BinarySwitches").toString();
			
			Gson gson = new Gson();
			Object[] test = gson.fromJson(status, Object[].class);
			Map<String, Object> json = utils.getJsonMap(test[0].toString());
			plugStatus = json.get("pos").toString();
			
			return plugStatus.equals("255.0");
		}
		catch (Exception e) {
			error.setErrorMessage("Error occurred with getting status");
		}
		return false;
	}
	
	public boolean getQMotionStatus(String token) {
		// TODO Figure out what to do with this method
		
		try {
			String motionStatus;
			String gatewayStatus = utils.getGatewayStatusJSON("0000003652", "2166", token);
			String status = utils.getJsonMap(gatewayStatus).get("BinarySensors").toString();
			
			Gson gson = new Gson();
			Object[] test = gson.fromJson(status, Object[].class);
			Map<String, Object> json = utils.getJsonMap(test[0].toString());
			motionStatus = json.get("armed").toString();
			error.clearErrorMessage();
			return motionStatus.equals("1.0");
		}
		catch (Exception e) {
			error.setErrorMessage("Error occurred with getting status");
		}
		return false;
	}

}
