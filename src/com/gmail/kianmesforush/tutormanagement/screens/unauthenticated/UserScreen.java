package com.gmail.kianmesforush.tutormanagement.screens.unauthenticated;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;

public class UserScreen extends Screen {
	private final JLabel messageLabel = new JLabel("Click on a button");
	
	public JComponent show(JPanel panel) {
		panel.add(messageLabel);
		return panel;
	}
}
