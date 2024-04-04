package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.components.UserMgmtComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.popups.EditUserPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserMgmtScreen extends Screen {
	//Holds add btn and other save/back/filter btns (maybe move add button below with rest)
	private final JPanel btnsPanel = new JPanel();

	private final JButton backBtn = new JButton("Back");
	private final JButton saveBtn = new JButton("Save");
	private final JButton addBtn = new JButton("Add user");

	private final UserType type;

	private ArrayList<User> users;

	public UserMgmtScreen(ArrayList<User> users, UserType type) {
		loadData(users);
		this.type = type;
	}

	public void loadData(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getData() {
		return users;
	}
	
	public UserType getType() {
		return type;
	}

	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		//This must be initialized in #show() to ensure duplicate UserMgmtComponents
		//are not made if #show() is called numerous times
		//Container for list of UserMgmtComponents
		JPanel listPanel = new JPanel(new GridLayout(users.size(), 1));
		
		for (int i = 0; i < users.size(); i++) {
			listPanel.add(new UserMgmtComponent(users, i).show(new JPanel()));
		}
		
		backBtn.addActionListener(new BackBtnPressed());
		btnsPanel.add(backBtn);
		
		addBtn.addActionListener(new AddBtnPressed());
		btnsPanel.add(addBtn);

		saveBtn.addActionListener(new SaveBtnPressed());
		btnsPanel.add(saveBtn);
		
		
		// https://stackoverflow.com/questions/30292519/scrollpane-adding-to-grid-layout
		panel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(addBtn, StyleType.PRIMARY);
		StylingManager.stylize(saveBtn, StyleType.PRIMARY);
		StylingManager.stylize(btnsPanel, StyleType.PRIMARY);

		return panel;
	}

	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}

	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (type == UserType.TUTOR) {
				DataManager.tutors = users;
			} else {
				DataManager.tutees = users;
			}
			ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen());
		}
	}

	private class AddBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new EditUserPopup(type));
		}
	}
}
