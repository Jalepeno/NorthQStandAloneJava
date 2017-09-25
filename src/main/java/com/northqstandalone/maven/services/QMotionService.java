package com.northqstandalone.maven.services;

import java.io.IOException;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

public class QMotionService {


	private NorthQRestfulUtils utils;

	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;
	}

	// Requires: requires a token
	// Returns: returns the http response
	public Response armMotion(String token) throws IOException, HTTPException, Exception {
		utils = new NorthQRestfulUtils();
		System.out.println(utils == null);
		Form form = new Form();
		form.param("user", "2166");
		form.param("token", token);
		form.param("gateway_id", "0000003652");
		form.param("node_id", "3");
		Response response = utils.getHttpPostResponse("https://homemanager.tv/main/reArmUserComponent", form);
		return response;
	}

	// Requires: requires a token
	// Returns: returns the http response
	public Response disarmMotion(String token) throws IOException, HTTPException, Exception {
		utils = new NorthQRestfulUtils();
		Form form = new Form();
		form.param("user", "2166");
		form.param("token", token);
		form.param("gateway_id", "0000003652");
		form.param("node_id", "3");
		Response response = utils.getHttpPostResponse("https://homemanager.tv/main/disArmUserComponent", form);
		return response;
	}
}
