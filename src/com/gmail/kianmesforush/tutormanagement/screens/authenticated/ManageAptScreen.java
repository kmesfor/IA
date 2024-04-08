package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.components.ManageAptComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAptScreen extends Screen {
	private final JPanel btnPanel = new JPanel();
	private final JPanel aptsPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton createAptBtn = new JButton("Create appointment");
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		backBtn.addActionListener(new BackBtnPressed());
		createAptBtn.addActionListener(new CreateAptBtnPressed());
		btnPanel.add(backBtn);
		btnPanel.add(createAptBtn);
		
		//Ensures there are at least 10 rows (styling)
		aptsPanel.setLayout(new GridLayout(Math.max(DataManager.appointments.size(), 10), 1));
		if (DataManager.appointments.isEmpty()) {
			JLabel emptyLabel = new JLabel("No appointments created");
			StylingManager.stylize(emptyLabel, StyleType.ERROR);
			aptsPanel.add(emptyLabel);
		} else {
			for (int i = 0; i < DataManager.appointments.size(); i++) {
				aptsPanel.add((new ManageAptComponent(DataManager.appointments, i)).show(new JPanel()));
			}
			
		}
		
		panel.add(btnPanel, BorderLayout.SOUTH);
		panel.add(aptsPanel, BorderLayout.CENTER);
		
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
