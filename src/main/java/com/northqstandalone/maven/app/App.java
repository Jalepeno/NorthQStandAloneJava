package com.northqstandalone.maven.app;

import com.northqstandalone.maven.controllers.QMotionController;
import com.northqstandalone.maven.controllers.QPlugController;
import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.view.View;

public class App {
	public static void main(String[] args) {

		// View
		View view = new View();

		// QPlug
		QPlugModel qPlugModel = new QPlugModel();
		QPlugController qPlugController = new QPlugController(view, qPlugModel);

		// QMotion
		QMotionModel qMotionModel = new QMotionModel();
		QMotionController qMotionController = new QMotionController(view, qMotionModel);
		
		view.setVisible(true);
	}
}