package org.candidates.ibo.kgb197.tutormanagement.screens.authenticated;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHoursScreen extends Screen {
	
	private final JLabel tutorLabel = new JLabel("Tutors");
	private final JLabel tuteeLabel = new JLabel("Tutees");
	private final JButton backBtn = new JButton("Back");
	
	private final JPanel contentPanel = new JPanel(new GridLayout(0, 2));
	
	/**
	 * Display a screen listing the completed tutoring hours
	 * @param panel the JPanel to display components on
	 * @return a JComponent containing the Screen's rendered components
	 */
	public JComponent show(JPanel panel) {
		//Use BorderLayout for the main panel
		panel.setLayout(new BorderLayout());
		
		//Text label and blank spacing
		contentPanel.add(tutorLabel);
		contentPanel.add(new JPanel());
		
		//Iterate through each tutor
		for (User tutor: DataManager.tutors) {
			//Add the tutor name and hours completed to the contentPanel
			JLabel label = new JLabel(tutor.getName());
			JLabel text = new JLabel(tutor.getHoursCompleted() + " hours tutored");
			contentPanel.add(label);
			contentPanel.add(text);
			//Stylize components
			StylingManager.stylize(label, StyleType.SECONDARY);
			StylingManager.stylize(text, StyleType.SECONDARY);
			
		}
		
		//Text label and blank spacing
		contentPanel.add(tuteeLabel);
		contentPanel.add(new JPanel());
		
		//Iterate through each tutee
		for (User tutee : DataManager.tutees) {
			//Add the tutee name and hours completed to the contentPanel
			JLabel label = new JLabel(tutee.getName());
			JLabel text = new JLabel(tutee.getHoursCompleted() + " hours of tutoring received");
			contentPanel.add(label);
			contentPanel.add(text);
			//Stylize components
			StylingManager.stylize(label, StyleType.SECONDARY);
			StylingManager.stylize(text, StyleType.SECONDARY);
		}
		
		//Add sub panel
		panel.add(contentPanel, BorderLayout.CENTER);
		
		//Set up back button
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		//Stylize
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
