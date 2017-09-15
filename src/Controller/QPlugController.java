package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;

import Models.QPlugModel;
import Services.QPlugService;
import View.GUI;

public class QPlugController {

	private QPlugModel model;
	private GUI view;
	private QPlugService service;

	public QPlugController(GUI view, QPlugModel model) {
		this.view = view;
		this.model = model;

		this.service = new QPlugService();
		
		// Enable event listener
		this.view.addQPlugListener(new addQPlugListener());
	}

	// TODO: Inject model
	// @Autowired
	// public void setQPlugModel(QPlugModel model) {
	// this.model = model;
	// }

	// TODO: Inject view
	// @Autowired
	// public void setView(GUI view) {
	// this.view = view;
	// }

	// TODO: Inject service
	// @Autowired
	// public void setQPlugService(IQPlugService service) {
	// this.service = service;
	// }

	public void turnOn() {
		model.setStatus(1);
	}

	public void turnOff() {
		model.setStatus(0);
	}

	public int getStatus() {
		return model.getStatus();
	}

}

// Action listener to receive event from view
class addQPlugListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		System.out.println("QPlug clicked");
	}

}
