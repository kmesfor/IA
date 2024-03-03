package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.datatypes.ScreenPopup;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.UserMgmtScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EditUserPopup extends ScreenPopup {
	private final JLabel nameLabel = new JLabel("Name:");
	private final JLabel availabilityLabel = new JLabel("Availability:");
	private JLabel classesLabel;
	private JLabel skillsLabel;
	private JLabel proficienciesLabel;
	
	private final JTextField nameField = new JTextField("New User");
	
	private final JButton saveBtn = new JButton("Save");
	private final JButton cancelBtn = new JButton("Cancel");
	
	private final JPanel namePanel = new JPanel();
	private final JPanel upperPanel = new JPanel();
	private final JPanel btnsPanel = new JPanel();
	private final JPanel contentPanel = new JPanel();
	
	private final HashMap<TutoringSession, JCheckBox> availabilityCheckboxes = new HashMap<>();
	private final HashMap<ClassName, JCheckBox> classCheckboxes = new HashMap<>();
	private final HashMap<Skill, JCheckBox> skillCheckboxes = new HashMap<>();
	private final HashMap<Proficiency, JCheckBox> proficiencyCheckboxes = new HashMap<>();
	
	private final UserType userType;
	private User existingData = null;
	
	public EditUserPopup(UserType userType, User existingData) {
		this.userType = userType;
		initializeLabels();
		this.existingData = existingData;
	}
	public EditUserPopup(UserType userType) {
		this.userType = userType;
		initializeLabels();
	}
	
	public JComponent show(JPanel panel) {
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(namePanel, BorderLayout.NORTH);
		upperPanel.add(new JLabel("User type: " + userType));
		
		cancelBtn.addActionListener(new CancelBtnPressed());
		saveBtn.addActionListener(new SaveBtnPressed());
		btnsPanel.add(saveBtn);
		btnsPanel.add(cancelBtn);
		
		//make rows the # of labels + the # of options
		int rows = (4 + DataManager.sessions.size() + DataManager.classNames.size() + DataManager.skills.size() + DataManager.proficiencies.size());
		
		contentPanel.setLayout(new GridLayout(rows, 1));
		populateCheckboxes();
		
		panel.setLayout(new BorderLayout());
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		panel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
		
		//https://stackoverflow.com/questions/5328405/jpanel-padding-in-java
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		if (existingData != null) fillData(existingData);
		
		return panel;
	}
	
	private void fillData(User userData) {
		nameField.setText(userData.getName());
		availabilityCheckboxes.forEach((session, checkbox) ->{
			if (userData.availability.contains(session)) {
				checkbox.setSelected(true);
			}
		});
		classCheckboxes.forEach((className, checkbox) ->{
			if (userData.classNames.contains(className)) {
				checkbox.setSelected(true);
			}
		});
		skillCheckboxes.forEach((skill, checkbox) ->{
			if (userData.skills.contains(skill)) {
				checkbox.setSelected(true);
			}
		});
		proficiencyCheckboxes.forEach((proficiency, checkbox) ->{
			if (userData.proficiencies.contains(proficiency)) {
				checkbox.setSelected(true);
			}
		});
	}
	
	private void populateCheckboxes() {
		contentPanel.add(availabilityLabel);
		for (TutoringSession session : DataManager.sessions) {
			availabilityCheckboxes.put(session, new JCheckBox(session.getInfo()));
			contentPanel.add(availabilityCheckboxes.get(session));
		}
		contentPanel.add(classesLabel);
		for (ClassName className : DataManager.classNames) {
			classCheckboxes.put(className, new JCheckBox(className.getInfo()));
			contentPanel.add(classCheckboxes.get(className));
		}
		contentPanel.add(skillsLabel);
		for (Skill skill : DataManager.skills) {
			skillCheckboxes.put(skill, new JCheckBox(skill.getInfo()));
			contentPanel.add(skillCheckboxes.get(skill));
		}
		contentPanel.add(proficienciesLabel);
		for (Proficiency proficiency : DataManager.proficiencies) {
			proficiencyCheckboxes.put(proficiency, new JCheckBox(proficiency.getInfo()));
			contentPanel.add(proficiencyCheckboxes.get(proficiency));
		}
	}
	
	private void initializeLabels() {
		if (userType == UserType.TUTOR) {
			classesLabel = new JLabel("Prior classes:");
			skillsLabel = new JLabel("Skills:");
			proficienciesLabel = new JLabel("Proficiencies:");
		} else {
			classesLabel = new JLabel("Needs help in classes:");
			skillsLabel = new JLabel("Needs help with skills:");
			proficienciesLabel = new JLabel("Need help with proficiencies:");
		}
	}
	
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO: make this work for editing user data as well by adding a isNewUser field? and changing accordingly?
			User user = new User(nameField.getText(), userType);
			availabilityCheckboxes.forEach((session, checkbox) -> {
				if (checkbox.isSelected()) user.availability.add(session);
			});
			classCheckboxes.forEach((className, checkbox) -> {
				if (checkbox.isSelected()) user.classNames.add(className);
			});
			skillCheckboxes.forEach((skill, checkbox) -> {
				if (checkbox.isSelected()) user.skills.add(skill);
			});
			proficiencyCheckboxes.forEach((proficiency, checkbox) -> {
				if (checkbox.isSelected()) user.proficiencies.add(proficiency);
			});
			if (ScreenManager.getCurrentScreen() instanceof UserMgmtScreen) {
				// Use this instead of accessing DataManager because this will also account
				// for any changes made previously before user was edited
				ArrayList<User> data = ((UserMgmtScreen) ScreenManager.getCurrentScreen()).getData();
				if (existingData == null) {
					data.add(user);
				} else {
					data.set(data.indexOf(existingData), user);
				}
				if (userType == UserType.TUTOR && ((UserMgmtScreen) ScreenManager.getCurrentScreen()).getType() == UserType.TUTOR) {
					ScreenManager.setCurrentScreen(new UserMgmtScreen(data, UserType.TUTOR));
				} else {
					ScreenManager.setCurrentScreen(new UserMgmtScreen(data, UserType.TUTEE));
				}
				ScreenManager.closePopup();
			}
		}
	}
	
	private static class CancelBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
}
