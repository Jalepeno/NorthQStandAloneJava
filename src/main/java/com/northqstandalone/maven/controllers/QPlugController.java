package com.northqstandalone.maven.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.services.QPlugService;
import com.northqstandalone.maven.view.View;

public class QPlugController {

    private QPlugModel model;
    private View view;
    private QPlugService service;

    @Autowired
	public void setQPlugService(QPlugService service) {
		this.service = service;
	}
	
	@Autowired
	public void setQPlugModel(QPlugModel model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;

		// Enable event listener
		this.view.addQPlugListener(new addQPlugListener(service, model));
	}

    public int getStatus() {
        return model.getStatus();
    }

}

// Action listener to receive event from view
class addQPlugListener implements ActionListener {

    private QPlugService service;
    private QPlugModel model;

    public addQPlugListener(QPlugService service, QPlugModel model) {
        this.service = service;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(model.getStatus());
        if (model.getStatus() == 0) {
            service.turnOnPlug();
            model.setStatus(1);
        } else {
            service.turnOffPlug();
            model.setStatus(0);
        }
    }

}
