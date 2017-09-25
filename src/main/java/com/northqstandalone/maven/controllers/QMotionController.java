package com.northqstandalone.maven.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.controllers.StatusController.IQMotionListener;

import com.northqstandalone.maven.models.ErrorModel;

import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.services.QMotionService;
import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.view.View;

public class QMotionController implements IQMotionListener{

	private QMotionModel model;
	private View view;
	private QMotionService service;
	private String token;
	private boolean qMotionStatus;
	private ErrorModel error;

	@Autowired
	public void setQMotionService(QMotionService service) {
		this.service = service;
	}

	@Autowired
	public void setQMotionModel(QMotionModel model) {
		this.model = model;
	}
	
	@Autowired
	public void setErrorModel(ErrorModel error) {
		this.error = error;
	}

	public void setView(View view, String token, boolean status) {
		this.view = view;
		this.token = token;
		this.qMotionStatus = status;

		setQMotionStatus(status);

		// Enable event listener
		this.view.addQMotionListener(new addQMotionListener(service, model, view, error));
	}

	private void setQMotionStatus(boolean status) {
		// Change view and model based on status
		if (qMotionStatus) {
			// If motion sensor is armed
			model.setArmed(true);
			view.setMotionButtonText(0);
		} else {
			// If motion sensor is disarmed
			model.setArmed(false);
			view.setMotionButtonText(1);
		}
	}


	// Action listener to receive event from view
	class addQMotionListener implements ActionListener {

		private QMotionService service;
		private QMotionModel model;
		private View view;
		private ErrorModel error;

		public addQMotionListener(QMotionService service, QMotionModel model, View view, ErrorModel error) {
			this.service = service;
			this.model = model;
			this.view = view;
			this.error = error;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (model.isArmed() == true) {
				try {
					service.disarmMotion(token);
					model.setArmed(false);
					view.setMotionButtonText(1);
					error.clearErrorMessage();
				}
				catch (IOException e1) {
					error.setErrorMessage("An error has occurred reading file");
					view.setErrorMessage(error.ErrorMessage);
				}
				catch (HTTPException e2) {
					error.setErrorMessage("An error has occurred with the connection");
					view.setErrorMessage(error.ErrorMessage);
				}
				catch (Exception e3) {
					error.setErrorMessage("An error has occurred");
					view.setErrorMessage(error.ErrorMessage);
				}
			} 
			else if (model.isArmed() == false) {
				try {
					service.armMotion(token);
					model.setArmed(true);
					view.setMotionButtonText(0);
					error.clearErrorMessage();
				}
				catch (IOException e1) {
					error.setErrorMessage("An error has occurred reading file");
					view.setErrorMessage(error.ErrorMessage);
				}
				catch (HTTPException e2) {
					error.setErrorMessage("An error has occurred with the connection");
					view.setErrorMessage(error.ErrorMessage);
				}
				catch (Exception e3) {
					error.setErrorMessage("An error has occurred");
					view.setErrorMessage(error.ErrorMessage);
				}	
			}
		}
    
	@Override
	public void onMotionModelUpdate(QMotionModel motionModel) {
		// TODO Auto-generated method stub
		this.model = motionModel;
	}
    
}
