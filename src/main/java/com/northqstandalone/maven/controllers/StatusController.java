package com.northqstandalone.maven.controllers;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
	private final ScheduledExecutorService scheduler = Executors
		        .newScheduledThreadPool(1);
	
	
	
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;	
		
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                shutdown();
            }
        });
        startScheduleTask();
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
	
	
	
	
	public void startScheduleTask() {
	    /**
	    * not using the taskHandle returned here, but it can be used to cancel
	    * the task, or check if it's done (for recurring tasks, that's not
	    * going to be very useful)
	    */
	    final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
	        new Runnable() {
	            public void run() {
	                try {
	                	String response = utils.getGatewayStatusJSON("0000003652", "2166", "4po-bc5581bfaa6b3a3a2a1c");
	                	System.out.println(response);
	                }catch(Exception ex) {
	                    ex.printStackTrace(); //or logger would be better
	                }
	            }
	        }, 0, 15, TimeUnit.SECONDS);
	    }
	
	private void getDataFromDatabase() {
        System.out.println("getting data...");
    }

    public void shutdown() {
        System.out.println("shutdown...");
        if(scheduler != null) {
            scheduler.shutdown();
        }
    }

}
