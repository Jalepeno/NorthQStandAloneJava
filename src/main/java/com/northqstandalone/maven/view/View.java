package com.northqstandalone.maven.view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class View extends JFrame {

	private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	private JPanel motionInfoPanel = new JPanel(new BorderLayout(10, 10));

	BufferedImage turnedOnImage = ImageIO.read(new File("bulbOn.png"));
	BufferedImage turnedOffImage = ImageIO.read(new File("bulbOff.png"));

	private ImageIcon lightOnIcon = new ImageIcon(turnedOnImage);
	private ImageIcon lightOffIcon = new ImageIcon(turnedOffImage);

	private String motionString = new String(
			"Temperature: " + "\n" + "Humidity: " + "\n" + "Light Intensity: " + "\n" + "Motion: ");

	private JButton lightButton = new JButton("QPlug", lightOffIcon);
	private JTextArea motionInfo = new JTextArea(motionString);
	private JCheckBox combineCheckBox = new JCheckBox("light on motion");

	public View() throws IOException {

		super("Q Equalizer!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(300, 300));
		setResizable(false);

		setLayout(new BorderLayout(10, 10));

		motionInfoPanel.add(motionInfo, BorderLayout.WEST);
		motionInfo.setOpaque(false);

		mainPanel.add(lightButton, BorderLayout.WEST);
		mainPanel.add(motionInfoPanel, BorderLayout.EAST);
		mainPanel.add(combineCheckBox, BorderLayout.SOUTH);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(mainPanel);

		lightButton.setHorizontalTextPosition(SwingConstants.CENTER);
		lightButton.setVerticalTextPosition(SwingConstants.BOTTOM);

		// Added method so UI starts in middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	public void addQPlugListener(ActionListener listenForQPlugButton) {
		lightButton.addActionListener(listenForQPlugButton);

	}

	public void clickButton(int status) {
		if (status == 1) {
			lightButton.setIcon(lightOnIcon);
		} else if (status == 0) {
			lightButton.setIcon(lightOffIcon);
		}
	}

	public void addQMotionListener(ActionListener addQMotionListener) {
		// NOTHING!
	}
}
