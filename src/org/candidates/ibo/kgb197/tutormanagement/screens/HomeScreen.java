package org.candidates.ibo.kgb197.tutormanagement.screens;

import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.TutorManagement;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends Screen {
	
	private final JButton authBtn = new JButton("Authenticated User Login");
	private final JButton exitBtn = new JButton("Exit");
	private final JButton loadSampleDataBtn = new JButton("Load Sample Data");
	
	private final JPanel btnsPanel = new JPanel(new BorderLayout());
	private final JPanel authBtnPanel = new JPanel();
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		authBtn.addActionListener(new AuthBtnPressed());
		authBtnPanel.add(authBtn);
		
		exitBtn.addActionListener(new ExitBtnPressed());
		btnsPanel.add(exitBtn, BorderLayout.WEST);
		
		loadSampleDataBtn.addActionListener(new LoadSampleDataBtnPressed());
		btnsPanel.add(loadSampleDataBtn, BorderLayout.EAST);
		
		panel.add(authBtnPanel, BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(authBtn, StyleType.PRIMARY);
		StylingManager.stylize(exitBtn, StyleType.SECONDARY);
		StylingManager.stylize(loadSampleDataBtn, StyleType.SECONDARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
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
	
	private static class LoadSampleDataBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TutorManagement.loadSampleData();
		}
	}
}
