package org.candidates.ibo.kgb197.tutormanagement.popups;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.ScreenPopup;
import org.candidates.ibo.kgb197.tutormanagement.screens.authenticated.UserMgmtScreen;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralData;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.UserType;

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
	private final JLabel classesLabel = new JLabel();
	private final JLabel skillsLabel = new JLabel();
	private final JLabel proficienciesLabel = new JLabel();
	private final JLabel typeLabel = new JLabel();
	
	private final JTextField nameField = new JTextField("New User");
	
	private final JButton cancelBtn = new JButton("Cancel");
	private final JButton saveBtn = new JButton("Save");
	
	private final JPanel namePanel = new JPanel();
	private final JPanel upperPanel = new JPanel();
	private final JPanel btnsPanel = new JPanel();
	private final JPanel contentPanel = new JPanel();
	
	private final HashMap<GeneralData, JCheckBox> availabilityCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JCheckBox> classCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JCheckBox> skillCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JCheckBox> proficiencyCheckboxes = new HashMap<>();
	
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
		panel.setLayout(new BorderLayout());
		//Source: https://stackoverflow.com/questions/5328405/jpanel-padding-in-java
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(namePanel, BorderLayout.NORTH);
		upperPanel.add(typeLabel);
		
		contentPanel.setLayout(new GridLayout(0, 1));
		
		cancelBtn.addActionListener(new CancelBtnPressed());
		saveBtn.addActionListener(new SaveBtnPressed());
		btnsPanel.add(cancelBtn);
		btnsPanel.add(saveBtn);
		
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(nameLabel, StyleType.PRIMARY);
		StylingManager.stylize(nameField, StyleType.SECONDARY);
		StylingManager.stylize(typeLabel, StyleType.SECONDARY);
		
		StylingManager.stylize(availabilityLabel, StyleType.PRIMARY);
		StylingManager.stylize(classesLabel, StyleType.PRIMARY);
		StylingManager.stylize(skillsLabel, StyleType.PRIMARY);
		StylingManager.stylize(proficienciesLabel, StyleType.PRIMARY);
		StylingManager.stylize(saveBtn, StyleType.PRIMARY);
		StylingManager.stylize(cancelBtn, StyleType.SECONDARY);
		
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		if (existingData != null) fillData(existingData);
		populateCheckboxes();
		
		return panel;
	}
	
	private void fillData(User userData) {
		nameField.setText(userData.getName());
		availabilityCheckboxes.forEach((session, checkbox) ->{
			if (DataManager.listContains(userData.availability, session)) {
				checkbox.setSelected(true);
			}
		});
		classCheckboxes.forEach((className, checkbox) ->{
			if (DataManager.listContains(userData.classNames, className)) {
				checkbox.setSelected(true);
			}
		});
		skillCheckboxes.forEach((skill, checkbox) ->{
			if (DataManager.listContains(userData.skills, skill)) {
				checkbox.setSelected(true);
			}
		});
		proficiencyCheckboxes.forEach((proficiency, checkbox) ->{
			if (DataManager.listContains(userData.proficiencies, proficiency)) {
				checkbox.setSelected(true);
			}
		});
	}
	
	private void populateCheckboxes() {
		contentPanel.add(availabilityLabel);
		for (GeneralData session : DataManager.sessions) {
			availabilityCheckboxes.put(session, new JCheckBox(session.getInfo()));
			contentPanel.add(availabilityCheckboxes.get(session));
			StylingManager.stylize(availabilityCheckboxes.get(session), StyleType.SECONDARY);
		}
		contentPanel.add(classesLabel);
		for (GeneralData className : DataManager.classNames) {
			classCheckboxes.put(className, new JCheckBox(className.getInfo()));
			contentPanel.add(classCheckboxes.get(className));
			StylingManager.stylize(classCheckboxes.get(className), StyleType.SECONDARY);
		}
		contentPanel.add(skillsLabel);
		for (GeneralData skill : DataManager.skills) {
			skillCheckboxes.put(skill, new JCheckBox(skill.getInfo()));
			contentPanel.add(skillCheckboxes.get(skill));
			StylingManager.stylize(skillCheckboxes.get(skill), StyleType.SECONDARY);
		}
		contentPanel.add(proficienciesLabel);
		for (GeneralData proficiency : DataManager.proficiencies) {
			proficiencyCheckboxes.put(proficiency, new JCheckBox(proficiency.getInfo()));
			contentPanel.add(proficiencyCheckboxes.get(proficiency));
			StylingManager.stylize(proficiencyCheckboxes.get(proficiency), StyleType.SECONDARY);
		}
	}
	
	private void initializeLabels() {
		typeLabel.setText("User type: " + userType);
		if (userType == UserType.TUTOR) {
			classesLabel.setText("Prior classes:");
			skillsLabel.setText("Skills:");
			proficienciesLabel.setText("Proficiencies:");
		} else {
			classesLabel.setText("Needs help in classes:");
			skillsLabel.setText("Needs help with skills:");
			proficienciesLabel.setText("Need help with proficiencies:");
		}
	}
	
	/**
	 * Creates and saves a new User object based on input data.
	 */
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Create a new User object using the name from the nameField text input
			User user = new User(nameField.getText(), userType);
			/*
			Loop through the checkboxes for availabilities, classes, skills, and proficiencies,
			if the checkbox is active, add the availability, class, skill, or proficiency to the user
			 */
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
				// Use the data stored within the Screen instead of accessing DataManager as this
				// will account for any changes made previously before the user was edited
				ArrayList<User> data = ((UserMgmtScreen) ScreenManager.getCurrentScreen()).getData();
				//If existingData == null, a new user is being created,
				// otherwise, an existing user is being updated
				if (existingData == null) {
					data.add(user);
				} else {
					data.set(data.indexOf(existingData), user);
				}
				//Return to the appropriate management screen
				if (userType == UserType.TUTOR && ((UserMgmtScreen) ScreenManager.getCurrentScreen()).getType() == UserType.TUTOR) {
					ScreenManager.setCurrentScreen(new UserMgmtScreen(data, UserType.TUTOR));
				} else {
					ScreenManager.setCurrentScreen(new UserMgmtScreen(data, UserType.TUTEE));
				}
				//Close the editing popup
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
