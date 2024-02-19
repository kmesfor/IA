package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.Screen;

import javax.swing.*;
import java.awt.*;

public class AuthUserPrimaryScreen implements Screen {
	private final JButton tutorMgmtBtn = new JButton("Tutor Management");
	private final JButton tuteeMgmtBtn = new JButton("Tutee Management");
	private final JButton generalMgmtBtn = new JButton("General Management");
	private final JButton createAptBtn = new JButton("Create Appointment");
	private final JButton viewHoursBtn = new JButton("View completed tutoring hours");
	private final JButton backBtn = new JButton("Back");
	
	public JComponent show() {
		//TODO: layout
		panel.add(tutorMgmtBtn);
		panel.add(tuteeMgmtBtn);
		panel.add(generalMgmtBtn);
		panel.add(createAptBtn);
		panel.add(viewHoursBtn);
		panel.add(backBtn);
		
		return panel;
	}
}
