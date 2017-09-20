package com.northqstandalone.maven.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NorthQRestfulUtils {

	// Requires: A URL target for a post http(s) service and a Form object
	// containing all parameters
	// Returns: A JSON string containing the json response
	public Response getHttpPostResponse(String target, Form parameters) {
		// Build the web target
		Client client = ClientBuilder.newBuilder().build();
		WebTarget webResource = client.target(target);

		// Carry out the post request
		Response clientResponse = webResource.request()
				.post(Entity.entity(parameters, MediaType.APPLICATION_FORM_URLENCODED));
		return clientResponse;
	}

	// Requires: A URL target for a post http(s) service and a Form object
	// containing all parameters
	// Returns: A JSON string containing the json response
	public Response getHttpGetResponse(String target) {
		// Build the web target
		Client client = ClientBuilder.newBuilder().build();
		WebTarget webResource = client.target(target);

		// Carry out the post request
		Response clientResponse = webResource.request().get();
		return clientResponse;
	}

	// Requires:
	// Returns: a string representation of JSON object returned by northQ restful
	// services
	public String getTokenJSON() throws IOException {
		Form form = new Form();
		ArrayList<String> info = logInInfo();
		// System.out.println(info.get(0));
		// System.out.println(info.get(1));

		form.param("username", info.get(0));
		form.param("password", info.get(1));
		Response response = getHttpPostResponse("https://homemanager.tv/token/new.json", form);
		// Test success of request
		if (response.getStatus() == 200) {
			String json = response.readEntity(String.class);
			response.close();
			return json;
		} else {
			response.close();
			throw new NullPointerException("token not recieved http error code: " + response.getStatus());
		}

	}

	public ArrayList<String> logInInfo() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:\\file.txt"));
		String[] info = null;
		ArrayList<String> list = new ArrayList<String>();
		String line = br.readLine();
		list.add(line);
		line = br.readLine();
		list.add(line);
		br.close();
		return list;
	}

	// Requires:
	// Returns: An http response
	public String getTokenString() throws IOException {
		return getJsonMap(getTokenJSON()).get("token").toString();
	}

	// Requires:
	// Returns: Returns true/false depending on whether or not token is still valid
	public boolean verifyToken() {
		return false;
	}

	// Requires: gatewayId, userId and a token (all strings)
	// Returns: A http response
	public Response getGetawayStatus(String gatewayId, String userId, String token) {
		// https://homemanager.tv/main/getGatewayStatus?gateway={{gateway_id}}&user={{user_id}}&token={{token}}
		String URL = "https://homemanager.tv/main/getGatewayStatus?gateway=" + gatewayId + "&user=" + userId + "&token="
				+ token;
		Response response = getHttpGetResponse(URL);
		System.out.println(response.readEntity(String.class));
		return response;
	}

	// Requires: a userId and a token both strings
	// Returns: A http response
	public Response getCurrentUserHouses(String userId, String token) {
		// https://homemanager.tv/main/getCurrentUserHouses?user={{user_id}}&token={{token}}
		String url = "https://homemanager.tv/main/getCurrentUserHouses?user=" + userId + "&token=" + token;
		return getHttpGetResponse(url);
	}

	// Requires: a houseId, a userId and a token (all strings)
	// Returns: An http response
	public Response getHouseGateways(String houseId, String userId, String token) {
		// https://homemanager.tv/main/getHouseGateways?house_id={{house_id}}&user={{user_id}}&token={{token}}
		String url = "https://homemanager.tv/main/getHouseGateways?house_id=" + houseId + "&user=" + userId + "&token="
				+ token;
		return getHttpGetResponse(url);
	}

	// Requires: string representations of userId,token, houseId,page
	// Returns: An http response
	public Response getNotifications(String userId, String token, String houseId, String page) {
		String url = "https://homemanager.tv/main/getUserNotifications?user=" + userId + "&token=" + token
				+ "&house_id=" + houseId + "&page=" + page;
		return getHttpGetResponse(url);
	}

	// Requires: a JSON formatted string
	// Returns: A map consisting of objects translated from JSON
	public Map<String, Object> getJsonMap(String jsonString) {
		return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
		}.getType());
	}
}
