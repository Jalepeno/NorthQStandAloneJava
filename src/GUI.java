import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {

	private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JToggleButton Button1 = new JToggleButton("Light");
    private JToggleButton Button2 = new JToggleButton("Motion");
    private JCheckBox checkbox = new JCheckBox("switch light on motion detect");
    
    public GUI() {

        super("Sample GUI");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(200, 200));
        setResizable(false);

        setLayout(new BorderLayout(10, 10));


        mainPanel.add(Button1, BorderLayout.WEST);
        mainPanel.add(Button2, BorderLayout.EAST);
        mainPanel.add(checkbox, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);
    }

}
