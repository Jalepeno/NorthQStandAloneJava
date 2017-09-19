package com.northqstandalone.maven.services;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class QMotionService {

    NorthQRestfulUtils services = new NorthQRestfulUtils();

    // Requires: requires a token
    // Returns: returns the http response
    public Response armMotion(String token) {
        Form form = new Form();
        form.param("user", "2166");
        form.param("token", token);
        form.param("gateway_id", "0000003652");
        form.param("node_id", "3");
        Response response = services.getHttpPostResponse("https://homemanager.tv/main/reArmUserComponent", form);
        return response;
    }

    // Requires: requires a token
    // Returns: returns the http response
    public Response disarmMotion(String token) {
        Form form = new Form();
        form.param("user", "2166");
        form.param("token", token);
        form.param("gateway_id", "0000003652");
        form.param("node_id", "3");
        Response response = services.getHttpPostResponse("https://homemanager.tv/main/disArmUserComponent", form);
        return response;
    }

    // Binary http request to Qmotion
    public void updateQMotionStatus(int status, String token) {
        /*
         * URL: https://homemanager.tv/main/setBinaryValue
         * Ã¢â‚¬Â¢ ON: user=2164&token=4pd-5f21e6aa764e71b78885&gateway=0000003009&node_id=2&pos=0
         * Ã¢â‚¬Â¢ OFF: user=2164&token=4pd-5f21e6aa764e71b78885&gateway=0000003009&node_id=2&pos=255
         */
        Form form = new Form();
        form.param("user", "2166");
        form.param("token", token);
        form.param("gateway", "0000003652");
        form.param("node_id", "3");
        String stat = "0";

        if (status > 0) {
            stat = "255";
        }
        form.param("pos", stat);
        System.out.println(services.getHttpPostResponse("https://homemanager.tv/main/setBinaryValue", form)
                .readEntity(String.class));

    }
}
