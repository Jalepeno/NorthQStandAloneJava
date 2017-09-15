import javax.swing.SwingUtilities;

import Controller.QPlugController;
import Models.QPlugModel;
import View.GUI;

public class Main {

	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
            	GUI view = new GUI();
                
//            	QPlugModel qPlugModel = new QPlugModel();
                
//                QPlugController qPlugController = new QPlugController(view,qPlugModel);
                
                view.setVisible(true);
            	
//                GUI app = new GUI();
//                app.setVisible(true);
            }
        });
	}

}
