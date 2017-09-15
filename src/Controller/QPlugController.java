package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;

import Models.QPlugModel;
import View.GUI;

public class QPlugController {

	private QPlugModel model;
	private GUI view;

	public QPlugController(GUI view, QPlugModel model) {
		this.view = view;
		this.model = model;
		
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
