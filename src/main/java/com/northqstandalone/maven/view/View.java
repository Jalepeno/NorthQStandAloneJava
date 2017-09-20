package com.northqstandalone.maven.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {

	private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JButton Button1 = new JButton("QPlug");
    private JButton Button2 = new JButton("QMotion");
    private JCheckBox checkbox = new JCheckBox("switch light on motion detect");
    
    public View() {

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
    
    public void addQPlugListener(ActionListener listenForQPlugButton){
    	Button1.addActionListener(listenForQPlugButton);
	}
    
    public void addQMotionListener(ActionListener listenForQMotionButton){
    	Button2.addActionListener(listenForQMotionButton);
	}

}
