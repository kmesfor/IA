package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.components.UserMgmtComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.popups.EditUserPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TutorMgmtScreen extends Screen {
	
	//Holds add btn and other save/back/filter btns (maybe move add button below with rest)
	private final JPanel controlPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton saveBtn = new JButton("Save");
	private final JButton addBtn = new JButton("Add user");
	private final JButton filterBtn = new JButton("Filter");
	private final ArrayList<Tutor> tutors;
	
	public TutorMgmtScreen(ArrayList<Tutor> tutors) {
		this.tutors = tutors;
	}
	
	public JComponent show(JPanel panel) {
		//This must be initialized in #show() to ensure duplicate UserMgmtComponents
		// are not made if #show() is called numerous times
		//Container for list of UserMgmtComponents
		JPanel listPanel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		// https://stackoverflow.com/questions/30292519/scrollpane-adding-to-grid-layout
		panel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
		panel.add(controlPanel, BorderLayout.SOUTH);
		
		listPanel.setLayout(new GridLayout(tutors.size(), 1));
		for (int i = 0; i < tutors.size(); i++) {
			listPanel.add(new UserMgmtComponent(tutors, i).show());
		}
		
		backBtn.addActionListener(new BackBtnPressed());
		saveBtn.addActionListener(new SaveBtnPressed());
		addBtn.addActionListener(new AddBtnPressed());
		controlPanel.add(backBtn);
		controlPanel.add(saveBtn);
		controlPanel.add(addBtn);
		controlPanel.add(filterBtn);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}
	
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DataManager.tutors = tutors;
			ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen());
		}
	}
	
	private static class AddBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new EditUserPopup(UserType.TUTOR));
		}
	}
}

