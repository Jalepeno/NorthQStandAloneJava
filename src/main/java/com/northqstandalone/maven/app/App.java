package com.northqstandalone.maven.app;

import java.io.IOException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.northqstandalone.maven.controllers.QMotionController;
import com.northqstandalone.maven.controllers.QPlugController;
import com.northqstandalone.maven.controllers.StatusController;
import com.northqstandalone.maven.controllers.TokenController;
import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.models.QPlugModel;
import com.northqstandalone.maven.view.View;

public class App {
	public static void main(String[] args) throws IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/config.xml");

		BeanFactory factory = context;

		// View
		View view = new View();

		TokenController tokenController = (TokenController) factory.getBean("tokenController");
		String token = tokenController.getToken();
		System.out.println("Token received: " + token);
		
		StatusController statusController = (StatusController) factory.getBean("statusController");
		
		QPlugController qPlugController = (QPlugController) factory.getBean("qPlugController");

		qPlugController.setView(view, token, false); // statusController.getQPlugStatus(token)

		QMotionController qMotionController = (QMotionController) factory.getBean("qMotionController");
		qMotionController.setView(view, token, statusController.getQMotionStatus(token));

		view.setVisible(true);

		/*
		 * Test code NorthQRestfulUtils service = new NorthQRestfulUtils(); String token
		 * = service.getToken(); service.getGetawayStatus("0000003652", "2166", token);
		 *
		 * QMotionService motion = new QMotionService();
		 * System.out.println(motion.armMotion(token).readEntity(String.class).toString(
		 * )); System.out.println(service.getNotifications("2166", token, "1828",
		 * "1").readEntity(String.class).toString()); motion.updateQMotionStatus(0,
		 * token);
		 * System.out.println(motion.disarmMotion(token).readEntity(String.class).
		 * toString());
		 */

	}
}