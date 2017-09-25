package com.northqstandalone.maven.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import com.northqstandalone.maven.models.BinarySensorModel;
import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.models.UserNotificationModel;
import com.northqstandalone.maven.models.getGatewayStatusModel;
import com.northqstandalone.maven.models.getUserNotificationsModel;
import com.northqstandalone.maven.services.IQPlugService;

import com.northqstandalone.maven.models.ErrorModel;

import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
  
	private final ScheduledExecutorService scheduler = Executors
		        .newScheduledThreadPool(1);
	public String token;
	private ArrayList<IQMotionListener> qMMListenerList;
	private ArrayList<IQPlugListener> qPMListenerList;
	
	private ErrorModel error;
	
	public StatusController() {
		qMMListenerList = new ArrayList<>();
		qPMListenerList = new ArrayList<>();
		
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public void setQMotionListener(IQMotionListener controller) {
		if(qMMListenerList != null) {
			qMMListenerList.add(controller);
		}
		
	}
	
	public void setQPlugListener(IQPlugListener controller) {
		if(qPMListenerList != null) {
			qPMListenerList.add(controller);
		}
		
	}
	



	@Autowired
	public void setNorthQService(NorthQRestfulUtils utils) {
		this.utils = utils;	
		
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                shutdown();
            }
        });
        startScheduleTask();
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
	
	public void getStatus() {
		if(token == null) {
			return;
		}
		try {
        	
			String response = utils.getGatewayStatusJSON("0000003652", "2166",token);
        	
        	Gson gson = new Gson();
        	getGatewayStatusModel statusModel= gson.fromJson(response, getGatewayStatusModel.class);
        	QMotionModel qMM = new QMotionModel();
        	qMM.setBinarySensorModel(statusModel.BinarySensors.get(0));
        	QPlugModel qPM = new QPlugModel();
        	qPM.setBinarySwitch(statusModel.BinarySwitches.get(0));
        	
        	for(IQMotionListener listener : qMMListenerList) {
        		listener.onMotionModelUpdate(qMM);
        	}
        	
        	for(IQPlugListener listener : qPMListenerList) {
        		listener.onPlugModelUpdate(qPM);
        	}
        	
        	
        }catch(Exception ex) {
            ex.printStackTrace(); //or logger would be better
        }
	}
	
	public void getNotifications() {
		if (token == null) {
			return;
		}
		try {
			System.out.println("getting notifications");
			Response response = utils.getNotifications("2166", token, "1828", "1");	
			String jsonString = response.readEntity(String.class);
			Gson gson = new Gson();
			getUserNotificationsModel notifications= gson.fromJson(jsonString, getUserNotificationsModel.class);
			
			UserNotificationModel latestNotification = notifications.UserNotifications.get(0);
			
			long latestNotifTimestamp = latestNotification.notification.timestamp * 1000;
			
			System.out.println("latest timestamp:"+latestNotifTimestamp);
			System.out.println("current timestamp:"+System.currentTimeMillis());
			
			long diff  = System.currentTimeMillis()-latestNotifTimestamp;
			if(diff < (30*60*1000)) { //30 min
				System.out.println("it has ben lass that 30 minutes since so turn on plugg!");
				
			}
			
			for(IQPlugListener listener : qPMListenerList) {
				listener.onNotificationUpdate((diff < (30*60*1000)));
        	}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
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
	
	
	

	public void startScheduleTask() {
	    /**
	    * not using the taskHandle returned here, but it can be used to cancel
	    * the task, or check if it's done (for recurring tasks, that's not
	    * going to be very useful)
	    */
	    final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
	        new Runnable() {
	            public void run() {
	            	
	               getStatus();
	               
	               getNotifications();
	            
	        }}, 0, 5, TimeUnit.SECONDS);
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
    
    public interface IQPlugListener{
    	public void onPlugModelUpdate(QPlugModel plugModel);
    	public void onNotificationUpdate(boolean timeLessThan30);
    }
    
    public interface IQMotionListener{
    	public void onMotionModelUpdate(QMotionModel motionModel);
    }

}
