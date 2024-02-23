package com.gmail.kianmesforush.tutormanagement.screens.unauthenticated;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;

public class UserScreen implements Screen {
	private final JLabel messageLabel = new JLabel("Click on a button");
	
	public JComponent show() {
		panel.add(messageLabel);
		return panel;
	}
}
