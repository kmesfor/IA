package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.components.UserMgmtComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.Popup;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.popups.TestPopup;

import javax.swing.*;
import java.awt.*;

public class TutorMgmtScreen implements Screen {
	
	//Holds add btn and other save/back/filter btns (maybe move add button below with rest)
	private final JPanel controlPanel = new JPanel();
	//Container for list of UserMgmtComponents
	private final JPanel listPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton saveBtn = new JButton("Save");
	private final JButton addBtn = new JButton("Add user");
	private final JButton filterBtn = new JButton("Filter");
	
	public JComponent show() {
		
		panel.setLayout(new BorderLayout());
		
		// https://stackoverflow.com/questions/30292519/scrollpane-adding-to-grid-layout
		panel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
		panel.add(controlPanel, BorderLayout.SOUTH);
		
		listPanel.setLayout(new GridLayout(DataManager.tutors.size(), 1));
		for (int i = 0; i < DataManager.tutors.size(); i++) {
			listPanel.add(new UserMgmtComponent(DataManager.tutors.get(i), i+1).show());
		}
		
		controlPanel.add(backBtn);
		controlPanel.add(saveBtn);
		controlPanel.add(addBtn);
		controlPanel.add(filterBtn);
		
		return panel;
	}
}

