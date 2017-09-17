package com.northqstandalone.maven.controllers;

public class QMotionController {

	QMotionModel model;
	GUI view;
	
	public QMotionController(GUI view, QMotionModel model) {
		this.model = model;
		this.view = view;
	}

}
