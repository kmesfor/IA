package org.candidates.ibo.kgb197.tutormanagement.screens.authenticated;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.components.UserMgmtComponent;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.UserType;
import org.candidates.ibo.kgb197.tutormanagement.popups.EditUserPopup;

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
	
	/**
	 * Display the User management screen for a list of tutors or tutees
	 * @param panel the panel to display components on
	 * @return a JComponent containing the Screen's rendered components
	 */
	public JComponent show(JPanel panel) {
		//Use BorderLayout for the main panel
		panel.setLayout(new BorderLayout());
		
		//This must be initialized in #show() to ensure duplicate UserMgmtComponents
		//are not made if #show() is called numerous times
		//Container for list of UserMgmtComponents
		JPanel listPanel = new JPanel(new GridLayout(users.size(), 1));
		
		//Add a UserMgmtComponent to the listPanel for each user
		for (int i = 0; i < users.size(); i++) {
			listPanel.add(new UserMgmtComponent(users, i).show(new JPanel()));
		}
		
		//Set up back button
		backBtn.addActionListener(new BackBtnPressed());
		btnsPanel.add(backBtn);
		
		//Set up add user button
		addBtn.addActionListener(new AddBtnPressed());
		btnsPanel.add(addBtn);

		//Set up save data button
		saveBtn.addActionListener(new SaveBtnPressed());
		btnsPanel.add(saveBtn);
		
		//Source: https://stackoverflow.com/questions/30292519/scrollpane-adding-to-grid-layout
		//Add subpanels to primary panel
		panel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		//Stylize
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
