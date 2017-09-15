package Services;

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

    // Requires: A URL target for a post http(s) service and a Form object containing all parameters
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

    // Requires:
    // Returns: a string representation of JSON object returned by northQ restful services
    public String getTokenJSON() {
        Form form = new Form();
        form.param("username", "dtu3");
        form.param("password", "dtu3");
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
    
	// Requires: a JSON formatted string
	// Returns: A map consisting of objects translated from JSON
	public Map<String, Object> getJsonMap(String jsonString) {
		return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
		}.getType());
	}
    
}
