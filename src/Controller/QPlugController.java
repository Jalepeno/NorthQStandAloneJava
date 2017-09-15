package Controller;

import Models.QPlugModel;
import View.GUI;

public class QPlugController {
	QPlugModel qPlugModel;
		
	public QPlugController(GUI view, QPlugModel qPlugModel) {
		// TODO Auto-generated constructor stub
	}
	
	public void turnOn(){
		qPlugModel.setStatus(1);
	}
	
	public void turnOff(){
		qPlugModel.setStatus(0);
	}
	
	public int getStatus(){
		return qPlugModel.getStatus(); 
	}

}
