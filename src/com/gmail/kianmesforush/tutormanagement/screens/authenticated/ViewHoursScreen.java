package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHoursScreen extends Screen {
	
	private final JLabel tutorLabel = new JLabel("Tutors");
	private final JLabel tuteeLabel = new JLabel("Tutees");
	private final JButton backBtn = new JButton("Back");
	
	private final JPanel blankPanel = new JPanel();
	private final JPanel contentPanel = new JPanel(new GridLayout(0, 2));
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		panel.add(tutorLabel);
		panel.add(blankPanel);
		
		for (User tutor: DataManager.tutors) {
			JLabel label = new JLabel(tutor.getName());
			JLabel text = new JLabel(tutor.getHoursCompleted() + " hours tutored");
			contentPanel.add(label);
			contentPanel.add(text);
			StylingManager.stylize(label, StyleType.PRIMARY);
			StylingManager.stylize(text, StyleType.SECONDARY);
			
		}
		
		panel.add(tuteeLabel);
		panel.add(blankPanel);
		
		for (User tutee : DataManager.tutees) {
			JLabel label = new JLabel(tutee.getName());
			JLabel text = new JLabel(tutee.getHoursCompleted() + " hours of tutoring received");
			contentPanel.add(label);
			contentPanel.add(text);
			StylingManager.stylize(label, StyleType.PRIMARY);
			StylingManager.stylize(text, StyleType.SECONDARY);
		}
		
		panel.add(contentPanel, BorderLayout.CENTER);
		
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		StylingManager.stylize(tutorLabel, StyleType.PRIMARY);
		StylingManager.stylize(tuteeLabel, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.PRIMARY);
		
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	public static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}
}
