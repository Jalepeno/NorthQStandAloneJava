package com.northqstandalone.maven.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class View extends JFrame {

	private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	private JPanel motionInfoPanel = new JPanel(new BorderLayout(10, 10));
	private JPanel motionButtonPanel = new JPanel(new BorderLayout(10,10));

	BufferedImage turnedOnImage = ImageIO.read(new File("bulbOn.png"));
	BufferedImage turnedOffImage = ImageIO.read(new File("bulbOff.png"));
	
	private ImageIcon lightOnIcon = new ImageIcon(turnedOnImage);
	private ImageIcon lightOffIcon = new ImageIcon(turnedOffImage);

	
	BufferedImage motionOnImage = ImageIO.read(new File("motionOn.png"));
	BufferedImage motionOffImage = ImageIO.read(new File("motionOff.png"));
	
	
	private ImageIcon motionOnImageIcon = new ImageIcon(motionOnImage);
	private ImageIcon motionOffImageIcon = new ImageIcon(motionOffImage);
	
//	private String motionString = new String(
//			"Temperature: " + "\n" + "Humidity: " + "\n" + "Light Intensity: " + "\n" + "Motion: ");
	private String armString = new String("Arm");
	private String disarmString = new String("Disarm");
	
	private JLabel motionInfoLabel = new JLabel("MOTION STATUS");
	private JLabel errorMessage = new JLabel(" ");
	private JLabel motionImage = new JLabel(motionOffImageIcon);
	private JButton lightButton = new JButton("QPlug", lightOffIcon);
	private JButton motionButton = new JButton("QMotion");
//	private JTextArea motionInfo = new JTextArea(motionString);
	private JCheckBox combineCheckBox = new JCheckBox("light on motion");

	public View() throws IOException {

		super("Q Equalizer!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(350, 300));
		setResizable(false);

		setLayout(new BorderLayout(10, 10));
		
		motionButtonPanel.add(motionButton, BorderLayout.SOUTH);
		motionButtonPanel.add(motionInfoLabel, BorderLayout.NORTH);

		
		
//		motionInfoPanel.add(motionInfo, BorderLayout.NORTH);
		motionInfoPanel.add(motionImage, BorderLayout.NORTH);
		motionInfoPanel.add(motionButtonPanel, BorderLayout.SOUTH);
//		motionInfo.setOpaque(false);

		mainPanel.add(lightButton, BorderLayout.EAST);
		mainPanel.add(errorMessage, BorderLayout.BEFORE_FIRST_LINE);
		mainPanel.add(motionInfoPanel, BorderLayout.WEST);
		mainPanel.add(combineCheckBox, BorderLayout.SOUTH);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(mainPanel);

		lightButton.setHorizontalTextPosition(SwingConstants.CENTER);
		lightButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		errorMessage.setForeground(Color.red);
		
		// Added method so UI starts in middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
	
	public boolean IsCombinedChecked() {
		return combineCheckBox.isSelected();
	}

	public void addQPlugListener(ActionListener listenForQPlugButton) {
		lightButton.addActionListener(listenForQPlugButton);

	}

	public void addQMotionListener(ActionListener listenQMotionListener) {
		motionButton.addActionListener(listenQMotionListener);
	}

	public void setIcon(int status) {
		if (status > 0) {
			lightButton.setIcon(lightOnIcon);
		} else if (status == 0) {
			lightButton.setIcon(lightOffIcon);
		}
	}

	public void setMotionButtonText(int status) {
		if (status == 0) {
			motionButton.setText(armString);
			setMotionInfoLabel(0);
		} else if (status == 1) {
			motionButton.setText(disarmString);
			setMotionInfoLabel(1);
		}
	}
	
	public void setMotionInfoLabel (int status) {
		if (status == 1) {
			motionInfoLabel.setText("MotionSensor: Armed");
		} else if (status == 0) {
			motionInfoLabel.setText("MotionSensor: Disarmed");
		}
	}
	
	public void setMotionImage(boolean active) {
		if(active) {
			motionImage.setIcon(motionOnImageIcon);
		}else {
			motionImage.setIcon(motionOffImageIcon);
		}
	}
	
	public void setErrorMessage(String message) {
		errorMessage.setText(message);
	}
}
