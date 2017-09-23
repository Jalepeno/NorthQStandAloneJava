package com.northqstandalone.maven.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.models.ErrorModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.view.View;

public class QPlugController {

	private QPlugModel model;
	private ErrorModel error;
	private View view;
	private QPlugService service;
	private String token;
	private boolean qPlugStatus;

	@Autowired
	public void setQPlugService(QPlugService service) {
		this.service = service;
	}

	@Autowired
	public void setQPlugModel(QPlugModel model) {
		this.model = model;
	}

	@Autowired
	public void setErrorModel(ErrorModel error) {
		this.error = error;
	}

	public void setView(View view, String token, boolean status) {
		this.view = view;
		this.token = token;
		this.qPlugStatus = status;

		setQPlugStatus(status);

		// Enable event listener
		this.view.addQPlugListener(new addQPlugListener(service, model, view, error));
	}

	private void setQPlugStatus(boolean status) {
		// Change view and model based on status

		if (qPlugStatus) {
			// If plug is on
			model.setStatus(1);
			view.setIcon(1);
		} else {
			// If plug is off
			model.setStatus(0);
			view.setIcon(0);
		}
	}

	public int getStatus() {
		return model.getStatus();
	}

}

// Action listener to receive event from view
class addQPlugListener implements ActionListener {

	private QPlugService service;
	private QPlugModel model;
	private View view;
	private ErrorModel error;

	public addQPlugListener(QPlugService service, QPlugModel model, View view, ErrorModel error) {
		this.service = service;
		this.model = model;
		this.view = view;
		this.error = error;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean status;
		
		if (model.getStatus() == 0) {
			try {
				status = service.turnOnPlug();
				if (status) {
					view.setIcon(1);
					model.setStatus(1);
					error.clearErrorMessage();
				}
				else {
					error.setErrorMessage("Error could not turn plug on");
				}
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

		} else {
			try {
				status = service.turnOffPlug();
				if (status) {
					view.setIcon(0);
					model.setStatus(0);
					error.clearErrorMessage();
				}
				else {
					error.setErrorMessage("Error could not turn plug off");
				}
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

}
