package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHoursScreen extends Screen {
	
	private final JButton backBtn = new JButton("Back");
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		JPanel upperPanel = new JPanel(new GridLayout(0, 2));
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		for (User tutor: DataManager.tutors) {
			upperPanel.add(new JLabel(tutor.getName()));
			upperPanel.add(new JLabel(tutor.getHoursCompleted() + " hours tutored"));
		}
		
		for (User tutee : DataManager.tutees) {
			upperPanel.add(new JLabel(tutee.getName()));
			upperPanel.add(new JLabel(tutee.getHoursCompleted() + " hours of tutoring received"));
		}
		
		panel.add(upperPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	public static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}
}
