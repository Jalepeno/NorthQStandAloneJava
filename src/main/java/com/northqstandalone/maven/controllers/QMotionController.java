package com.northqstandalone.maven.controllers;

import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.view.View;

public class QMotionController {

	QMotionModel model;
	View view;
	
	public QMotionController(View view, QMotionModel model) {
		this.model = model;
		this.view = view;
	}

}
