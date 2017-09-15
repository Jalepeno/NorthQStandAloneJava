package Controller;

import org.springframework.beans.factory.annotation.Autowired;

import Models.QPlugModel;
import View.GUI;

public class QPlugController {
	
	private QPlugModel model;
	private GUI view;
	
	public QPlugController(GUI view, QPlugModel model) {
		
	}
	
	// Injecting model to controller
//	@Autowired
//    public void setQPlugModel(QPlugModel model) {
//        this.model = model;
//    }
	
	// Injecting view to controller
//	@Autowired
//    public void setView(GUI view) {
//        this.view = view;
//    }
	
	public void turnOn(){
		model.setStatus(1);
	}
	
	public void turnOff(){
		model.setStatus(0);
	}
	
	public int getStatus(){
		return model.getStatus(); 
	}

}
