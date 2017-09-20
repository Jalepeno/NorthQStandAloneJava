package com.northqstandalone.maven.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.northqstandalone.maven.controllers.QMotionController;
import com.northqstandalone.maven.controllers.QPlugController;
import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.view.View;

public class App {
    public static void main(String[] args) {
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/config.xml");

		BeanFactory factory = context;

		// View
        View view = new View();
		
		QPlugController qPlugController = (QPlugController) factory.getBean("qPlugController");
		qPlugController.setView(view);
    	
        // QMotion
        QMotionModel qMotionModel = new QMotionModel();
        QMotionController qMotionController = new QMotionController(view, qMotionModel);

        view.setVisible(true);

        /*
         * Test code
         * NorthQRestfulUtils service = new NorthQRestfulUtils();
         * String token = service.getToken();
         * service.getGetawayStatus("0000003652", "2166", token);
         *
         * QMotionService motion = new QMotionService();
         * System.out.println(motion.armMotion(token).readEntity(String.class).toString());
         * System.out.println(service.getNotifications("2166", token, "1828", "1").readEntity(String.class).toString());
         * motion.updateQMotionStatus(0, token);
         * System.out.println(motion.disarmMotion(token).readEntity(String.class).toString());
         */

    }
}