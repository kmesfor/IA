package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthUserPrimaryScreen implements Screen {
	private final JButton tutorMgmtBtn = new JButton("Tutor Management");
	private final JButton tuteeMgmtBtn = new JButton("Tutee Management");
	private final JButton generalMgmtBtn = new JButton("General Management");
	private final JButton createAptBtn = new JButton("Create Appointment");
	private final JButton viewHoursBtn = new JButton("View completed tutoring hours");
	private final JButton backBtn = new JButton("Back");
	
	public JComponent show() {
		//TODO: layout
		tutorMgmtBtn.addActionListener(new TutorMgmtBtnPressed());
		
		panel.add(tutorMgmtBtn);
		panel.add(tuteeMgmtBtn);
		panel.add(generalMgmtBtn);
		panel.add(createAptBtn);
		panel.add(viewHoursBtn);
		panel.add(backBtn);
		
		return panel;
	}
	
	private static class TutorMgmtBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.setCurrentScreen(new TutorMgmtScreen());
		}
	}
}
