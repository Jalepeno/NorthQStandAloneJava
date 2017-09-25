package com.northqstandalone.maven.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import com.northqstandalone.maven.models.BinarySensorModel;
import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.models.getGatewayStatusModel;
import com.northqstandalone.maven.services.IQPlugService;

import com.northqstandalone.maven.models.ErrorModel;

import com.northqstandalone.maven.services.NorthQRestfulUtils;

public class StatusController {

	private NorthQRestfulUtils utils;
  
	private final ScheduledExecutorService scheduler = Executors
		        .newScheduledThreadPool(1);
	
	private ArrayList<IQMotionListener> qMMListenerList;
	private ArrayList<IQPlugListener> qPMListenerList;
	
	
	public StatusController() {
		qMMListenerList = new ArrayList<>();
		qPMListenerList = new ArrayList<>();
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
	

	private ErrorModel error;

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
	                try {
	                	String response = utils.getGatewayStatusJSON("0000003652", "2166", "4pr-f221ee918a5e94e07504");
	                	
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
	        }, 0, 5, TimeUnit.SECONDS);
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
    }
    
    public interface IQMotionListener{
    	public void onMotionModelUpdate(QMotionModel motionModel);
    }

}
