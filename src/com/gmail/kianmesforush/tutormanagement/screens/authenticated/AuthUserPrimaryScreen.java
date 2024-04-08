package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuthUserPrimaryScreen extends Screen {
	private final JButton tutorMgmtBtn = new JButton("Tutor Management");
	private final JButton tuteeMgmtBtn = new JButton("Tutee Management");
	private final JButton generalMgmtBtn = new JButton("General Management");
	private final JButton manageAptsBtn = new JButton("Manage Appointments");
	private final JButton viewHoursBtn = new JButton("View completed tutoring hours");
	private final JButton backBtn = new JButton("Back");
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new GridLayout(0, 1));
		
		tutorMgmtBtn.addActionListener(new TutorMgmtBtnPressed());
		tuteeMgmtBtn.addActionListener(new TuteeMgmtBtnPressed());
		generalMgmtBtn.addActionListener(new GeneralMgmtButtonPressed());
		manageAptsBtn.addActionListener(new ManageAptBtnPressed());
		viewHoursBtn.addActionListener(new ViewHoursBtnPressed());
		backBtn.addActionListener(new BackBtnPressed());
		
		panel.add(tutorMgmtBtn);
		panel.add(tuteeMgmtBtn);
		panel.add(generalMgmtBtn);
		panel.add(manageAptsBtn);
		panel.add(viewHoursBtn);
		panel.add(backBtn);
		
		StylingManager.stylize(tutorMgmtBtn, StyleType.PRIMARY);
		StylingManager.stylize(tuteeMgmtBtn, StyleType.PRIMARY);
		StylingManager.stylize(generalMgmtBtn, StyleType.PRIMARY);
		StylingManager.stylize(manageAptsBtn, StyleType.PRIMARY);
		StylingManager.stylize(viewHoursBtn, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		
		return panel;
	}
	
	private static class TutorMgmtBtnPressed implements ActionListener {
		// Create a clone of DataManager.tutors so the saved data is not edited until a save button is pressed
		// https://stackoverflow.com/questions/30074736/how-to-duplicate-an-array-list-in-java
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new UserMgmtScreen(new ArrayList<>(DataManager.tutors), UserType.TUTOR)); }
	}
	
	private static class TuteeMgmtBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new UserMgmtScreen(new ArrayList<>(DataManager.tutees), UserType.TUTEE)); }
	}
	
	private static class GeneralMgmtButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new GeneralMgmtScreen()); }
	}
	
	private static class ManageAptBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new ManageAptScreen()); }
	}
	
	private static class ViewHoursBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new ViewHoursScreen()); }
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new HomeScreen()); }
	}
}
