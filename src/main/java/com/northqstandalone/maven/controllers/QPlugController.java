package com.northqstandalone.maven.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.view.View;

public class QPlugController {

	private QPlugModel model;
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

	public void setView(View view, String token, boolean status) {
		this.view = view;
		this.token = token;
		this.qPlugStatus = status;

		setQPlugStatus(status);

		// Enable event listener
		this.view.addQPlugListener(new addQPlugListener(service, model, view));
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

	public addQPlugListener(QPlugService service, QPlugModel model, View view) {
		this.service = service;
		this.model = model;
		this.view = view;
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
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			try {
				status = service.turnOffPlug();
				if (status) {
					view.setIcon(0);
					model.setStatus(0);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
