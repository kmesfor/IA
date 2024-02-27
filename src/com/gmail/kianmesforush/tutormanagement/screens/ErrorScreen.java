package com.gmail.kianmesforush.tutormanagement.screens;

import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A top-level error screen that is shown when the program cannot continue
// and must be restarted. Ex: File not found error
public class ErrorScreen extends Screen {
	private final JButton exitBtn = new JButton("Exit");
	private final JLabel messageLabel;
	
	public ErrorScreen(String message) {
		messageLabel = new JLabel(message);
	}
	public JComponent show(JPanel panel) {
		exitBtn.addActionListener(new ExitBtnPressed());
		panel.add(messageLabel);
		panel.add(exitBtn);
		
		return panel;
	}
	
	private static class ExitBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { TutorManagement.exit(); }
	}
	
}
