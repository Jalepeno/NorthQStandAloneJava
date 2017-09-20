package com.northqstandalone.maven.services;

import java.io.IOException;

import javax.ws.rs.core.Form;

public class QPlugService {

    NorthQRestfulUtils nq = new NorthQRestfulUtils();

    public void turnOnPlug() throws IOException {
        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        updateQplugStatus(1, token);
    }

    public void turnOffPlug() throws IOException {
        String token = nq.getJsonMap(nq.getTokenJSON()).get("token").toString();
        updateQplugStatus(0, token);
    }

    public void getStatus() {
        // TODO: Optional, call to get status of QPlug
    }

    public void updateQplugStatus(int status, String token) {
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
        System.out.println(
                nq.getHttpPostResponse("https://homemanager.tv/main/setBinaryValue", form).readEntity(String.class));

    }
}