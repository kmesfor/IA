package com.gmail.kianmesforush.tutormanagement.screens;

import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.AuthUserPrimaryScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class AuthScreen extends Screen {
	
	/*
	https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html
	https://docs.oracle.com/javase/8/docs/api/javax/swing/JPasswordField.html
	https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
	https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
	 */
	
	private final JLabel usernameLabel = new JLabel("Username");
	private final JTextField usernameInput = new JTextField(10);
	
	private final JLabel passwordLabel = new JLabel("Password");
	private final JPasswordField passwordInput = new JPasswordField(10);
	
	private final JButton loginBtn = new JButton("Login");
	private final JButton backBtn = new JButton("Back");
	
	private final JLabel errorMessage = new JLabel("", SwingConstants.CENTER);
	
	private final JPanel fieldsPanel = new JPanel(new BorderLayout());
	private final JPanel btnsPanel = new JPanel(new BorderLayout());
	private final JPanel usernamePanel = new JPanel(new BorderLayout());
	private final JPanel passwordPanel = new JPanel(new BorderLayout());
	
	private String usernameCredential;
	private String passwordCredential;
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		errorMessage.setVisible(false);
		
		usernamePanel.add(usernameLabel, BorderLayout.NORTH);
		usernamePanel.add(usernameInput, BorderLayout.SOUTH);
		
		passwordPanel.add(passwordLabel, BorderLayout.NORTH);
		passwordPanel.add(passwordInput, BorderLayout.SOUTH);
		
		fieldsPanel.add(usernamePanel, BorderLayout.NORTH);
		fieldsPanel.add(passwordPanel, BorderLayout.SOUTH);
		
		backBtn.addActionListener(new BackBtnPressed());
		btnsPanel.add(backBtn, BorderLayout.WEST);
		
		loginBtn.addActionListener(new LoginBtnPressed());
		btnsPanel.add(loginBtn, BorderLayout.EAST);
		
		panel.add(errorMessage, BorderLayout.NORTH);
		panel.add(fieldsPanel, BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(usernameInput, StyleType.SECONDARY);
		StylingManager.stylize(passwordInput, StyleType.SECONDARY);
		StylingManager.stylize(usernameLabel, StyleType.PRIMARY);
		StylingManager.stylize(passwordLabel, StyleType.PRIMARY);
		StylingManager.stylize(loginBtn, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(errorMessage, StyleType.ERROR);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		loadCredentials();
		
		return panel;
	}
	
	//Event handlers
	private class LoginBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (usernameInput.getText().isEmpty()) {
				setErrorMessage("Username is empty");
			} else if (passwordInput.getPassword().length == 0) {
				setErrorMessage("Password is empty");
			} else if (!usernameInput.getText().equals(usernameCredential)) {
				setErrorMessage("Invalid username");
			} else if (!String.valueOf(passwordInput.getPassword()).equals(passwordCredential)) {
				setErrorMessage("Invalid password");
			} else {
				ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen());
			}
		}
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			Return to a new instance of the MainScreen, the ScreenManager handles
			opening and closing the appropriate windows
			 */
			ScreenManager.setCurrentScreen(new HomeScreen());
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
	
	private void loadCredentials() {
		try {
			Scanner scanner = new Scanner(new File("credentials.txt"));
			if (scanner.hasNext()) {
				usernameCredential = scanner.next();
			} else {
				setErrorMessage("Username not found in credentials.txt");
				return;
			}
			if (scanner.hasNext()) {
				passwordCredential = scanner.next();
			} else {
				setErrorMessage("Password not found in credentials.txt");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
	}
}
