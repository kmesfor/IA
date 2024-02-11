package com.gmail.kianmesforush.tutormanagement.screens;

import com.gmail.kianmesforush.tutormanagement.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthScreen implements Screen {
	
	/*
	https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html
	https://docs.oracle.com/javase/8/docs/api/javax/swing/JPasswordField.html
	https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
	 */
	
	private final JLabel usernameLabel = new JLabel("Username");
	private final JTextField username = new JTextField(10);
	private final JLabel passwordLabel = new JLabel("Password");
	private final JPasswordField password = new JPasswordField(10);
	private final JButton loginBtn = new JButton("Login");
	private final JButton backBtn = new JButton("Back");
	private final JLabel errorMessage = new JLabel("", SwingConstants.CENTER);
	private final JPanel fieldsPanel = new JPanel();
	private final JPanel btnsPanel = new JPanel();
	
	public JComponent show() {
		//TODO: Layout and functionality
		//Initializing panel content
		panel.setLayout(new BorderLayout());
		
		errorMessage.setForeground(Color.red);
		errorMessage.setVisible(false);
		
		panel.add(errorMessage, BorderLayout.NORTH);
		panel.add(fieldsPanel, BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		fieldsPanel.add(usernameLabel);
		fieldsPanel.add(username);
		fieldsPanel.add(passwordLabel);
		fieldsPanel.add(password);
		
		btnsPanel.setLayout(new BorderLayout());
		loginBtn.addActionListener(new LoginBtnPressed());
		btnsPanel.add(loginBtn, BorderLayout.WEST);
		
		backBtn.addActionListener(new BackBtnPressed());
		btnsPanel.add(backBtn, BorderLayout.EAST);
		
		return panel;
	}
	
	//Event handlers
	private class LoginBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (username.getText().isEmpty()) {
				setErrorMessage("Username is empty");
			} else if (password.getPassword().length == 0) {
				setErrorMessage("Password is empty");
			}
		}
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			Return to a new instance of the MainScreen, the ScreenManager handles
			opening and closing the appropriate windows
			 */
			ScreenManager.setCurrentScreen(new MainScreen());
		}
	}
	
	private void setErrorMessage(String text) {
		if (text.isEmpty()) {
			errorMessage.setText("");
			errorMessage.setVisible(false);
		} else {
			errorMessage.setText(text);
			errorMessage.setVisible(true);
		}
	}
}
