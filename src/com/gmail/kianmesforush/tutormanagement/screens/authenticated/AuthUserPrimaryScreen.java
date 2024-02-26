package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		backBtn.addActionListener(new BackBtnPressed());
		
		panel.add(tutorMgmtBtn);
		panel.add(tuteeMgmtBtn);
		panel.add(generalMgmtBtn);
		panel.add(createAptBtn);
		panel.add(viewHoursBtn);
		panel.add(backBtn);
		
		return panel;
	}
	
	private static class TutorMgmtBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new TutorMgmtScreen(new ArrayList<Tutor>(DataManager.tutors))); }
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new HomeScreen()); }
	}
}
