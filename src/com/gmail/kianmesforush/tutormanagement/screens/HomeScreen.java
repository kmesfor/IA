package com.gmail.kianmesforush.tutormanagement.screens;

import com.gmail.kianmesforush.tutormanagement.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.screens.unauthenticated.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen implements Screen {
	
	private final JButton continueBtn = new JButton("Continue");
	private final JButton authBtn = new JButton("Authenticated User Login");
	private final JButton exitBtn = new JButton("Exit");
	
	public JComponent show() {
		
		continueBtn.addActionListener(new ContinueBtnPressed());
		panel.add(continueBtn);
		
		authBtn.addActionListener(new AuthBtnPressed());
		panel.add(authBtn);
		
		exitBtn.addActionListener(new ExitBtnPressed());
		panel.add(exitBtn);
		
		return panel;
	}
	
	private static class ContinueBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.setCurrentScreen(new UserScreen());
		}
	}
	
	private static class AuthBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.setCurrentScreen(new AuthScreen());
		}
	}
	
	private static class ExitBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.exit();
		}
	}
}
