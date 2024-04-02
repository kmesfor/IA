package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.components.ManageAptComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.Appointment;
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
		backBtn.addActionListener(new BackBtnPressed());
		createAptBtn.addActionListener(new CreateAptBtnPressed());
		btnPanel.add(backBtn);
		btnPanel.add(createAptBtn);
		
		aptsPanel.setLayout(new GridLayout(0, 1));
		for (int i = 0; i < DataManager.appointments.size(); i++) {
			aptsPanel.add((new ManageAptComponent(DataManager.appointments, i)).panel);
		}
		
		panel.setLayout(new BorderLayout());
		panel.add(btnPanel, BorderLayout.SOUTH);
		panel.add(aptsPanel, BorderLayout.CENTER);
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
