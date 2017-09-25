package com.northqstandalone.maven.services;

import java.io.IOException;

import javax.ws.rs.core.Form;
import javax.xml.ws.http.HTTPException;

public class QPlugService {

    private NorthQRestfulUtils nq;
    
    public void setNorthQService(NorthQRestfulUtils utils) {
		this.nq = utils;
	}
    
    public boolean turnOnPlug() throws HTTPException, Exception {
        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        return updateQplugStatus(1, token);
    }

    public boolean turnOffPlug() throws HTTPException, Exception {
        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        return updateQplugStatus(0, token);
    }
    
    public boolean updateQplugStatus(int status, String token) throws IOException, HTTPException, Exception {
        Form form = new Form();
        form.param("user", "2166");
        form.param("token", token);
        form.param("gateway", "0000003652");
        form.param("node_id", "2");
        String stat = "0";

        if (status > 0) {
            stat = "255";
        }
        form.param("pos", stat);

        String response = nq.getHttpPostResponse("https://homemanager.tv/main/setBinaryValue", form).readEntity(String.class);
        return nq.getJsonMap(response).get("success").toString().equals("1.0");
    }
}