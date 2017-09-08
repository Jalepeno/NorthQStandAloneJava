import javax.swing.SwingUtilities;

public class HomeClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI app = new GUI();
                app.setVisible(true);
            }
        });
	}

}
