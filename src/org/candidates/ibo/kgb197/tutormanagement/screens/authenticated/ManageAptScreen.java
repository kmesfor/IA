package org.candidates.ibo.kgb197.tutormanagement.screens.authenticated;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.components.ManageAptComponent;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAptScreen extends Screen {
	private final JPanel btnPanel = new JPanel();
	private final JPanel aptsPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton createAptBtn = new JButton("Create appointment");
	
	/**
	 * Display the Appointment management screen for a list of Appointment objects
	 * @param panel the JPanel to display components on
	 * @return a JComponent containing the Screen's rendered components
	 */
	public JComponent show(JPanel panel) {
		//Use BorderLayout for the main panel
		panel.setLayout(new BorderLayout());
		
		//Set up back and add appointment buttons
		backBtn.addActionListener(new BackBtnPressed());
		createAptBtn.addActionListener(new CreateAptBtnPressed());
		btnPanel.add(backBtn);
		btnPanel.add(createAptBtn);
		
		//Ensures there are at least 10 rows (styling)
		aptsPanel.setLayout(new GridLayout(Math.max(DataManager.appointments.size(), 10), 1));
		if (DataManager.appointments.isEmpty()) {
			//Display a no appointments created error message if none are available
			JLabel emptyLabel = new JLabel("No appointments created");
			StylingManager.stylize(emptyLabel, StyleType.ERROR);
			aptsPanel.add(emptyLabel);
		} else {
			//Loop through each appointment and add a ManageAptComponent to the Screen
			for (int i = 0; i < DataManager.appointments.size(); i++) {
				aptsPanel.add((new ManageAptComponent(DataManager.appointments, i)).show(new JPanel()));
			}
			
		}
		
		//Add subpanels
		panel.add(btnPanel, BorderLayout.SOUTH);
		panel.add(aptsPanel, BorderLayout.CENTER);
		
		//Stylize
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(createAptBtn, StyleType.PRIMARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen());
		}
	}
	
	private static class CreateAptBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.setCurrentScreen(new CreateAptScreen());
		}
	}
}
