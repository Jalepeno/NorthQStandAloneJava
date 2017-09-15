package Controller;

import org.springframework.beans.factory.annotation.Autowired;

import Models.QPlugModel;
import View.GUI;

public class QPlugController {
	
	private QPlugModel model;
	
	public QPlugController() {
		
	}
	
	@Autowired
    public void setQPlugModel(QPlugModel model) {
        this.model = model;
    }
	
	@Autowired
    public void setView(GUI model) {
        
    }
	
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
