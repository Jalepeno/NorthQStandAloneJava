import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Controller.QMotionController;
import Controller.QPlugController;
import Models.QMotionModel;
import Models.QPlugModel;
import View.GUI;

public class Main {

	public static void main(String[] args) {
		
		// View
		GUI view = new GUI();
		
		// QPlug
		QPlugModel qPlugModel = new QPlugModel();
		QPlugController qPlugController = new QPlugController(view, qPlugModel);
		
		// QMotion
		QMotionModel qMotionModel = new QMotionModel();
		QMotionController qMotionController = new QMotionController(view, qMotionModel);
		
		view.setVisible(true);
	}
	
}
