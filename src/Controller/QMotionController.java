package Controller;

import Models.QMotionModel;
import View.GUI;

public class QMotionController {

	QMotionModel model;
	GUI view;
	
	public QMotionController(GUI view, QMotionModel model) {
		this.model = model;
		this.view = view;
	}

}
