package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.Screen;

import javax.swing.*;

public class AuthUserPrimaryScreen implements Screen {
	private final JLabel messageLabel = new JLabel("Click on a button");
	
	public JComponent show() {
		panel.add(messageLabel);
		return panel;
	}
}
