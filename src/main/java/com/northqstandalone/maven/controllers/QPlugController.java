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

	@Autowired
	public void setQPlugService(QPlugService service) {
		this.service = service;
	}

	@Autowired
	public void setQPlugModel(QPlugModel model) {
		this.model = model;
	}

	public void setView(View view, String token) {
		this.view = view;
		this.token = token;

		// Enable event listener
		this.view.addQPlugListener(new addQPlugListener(service, model, view));
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

		if (model.getStatus() == 0) {
			try {
				service.turnOnPlug();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.setStatus(1);
			view.clickButton(1);
		} else {
			try {
				service.turnOffPlug();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.setStatus(0);
			view.clickButton(0);
		}
		System.out.println(model.getStatus());
	}

}
