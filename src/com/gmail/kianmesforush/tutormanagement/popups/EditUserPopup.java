package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Popup;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserPopup extends Popup {
	JLabel nameLabel = new JLabel("Name:");
	JLabel availabilityLabel = new JLabel("Availability:");
	JLabel classesLabel;
	JLabel skillsLabel;
	JLabel proficienciesLabel;
	
	JTextField nameField = new JTextField("New User");
	
	JButton saveBtn = new JButton("Save");
	JButton cancelBtn = new JButton("Cancel");
	
	JPanel namePanel = new JPanel();
	JPanel btnsPanel = new JPanel();
	JPanel contentPanel = new JPanel();
	
	public EditUserPopup(UserType type) {
		if (type == UserType.TUTOR) {
			classesLabel = new JLabel("Prior classes:");
			skillsLabel = new JLabel("Skills:");
			proficienciesLabel = new JLabel("Proficiencies:");
		} else {
			classesLabel = new JLabel("Needs help in classes:");
			skillsLabel = new JLabel("Needs help with skills:");
			proficienciesLabel = new JLabel("Need help with proficiencies:");
		}
	}
	
	public void fillData(User userData) {
		nameField.setText(userData.getName());
	}
	
	
	public JComponent show(JPanel panel) {
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		
		cancelBtn.addActionListener(new CancelBtnPressed());
		btnsPanel.add(saveBtn);
		btnsPanel.add(cancelBtn);
		
		//make rows the # of labels + the # of options
		contentPanel.setLayout(new GridLayout(4, 1));
		contentPanel.add(availabilityLabel);
		contentPanel.add(classesLabel);
		contentPanel.add(skillsLabel);
		contentPanel.add(proficienciesLabel);
		
		panel.setLayout(new BorderLayout());
		panel.add(namePanel, BorderLayout.NORTH);
		panel.add(btnsPanel, BorderLayout.SOUTH);
		panel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
		
		//https://stackoverflow.com/questions/5328405/jpanel-padding-in-java
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}
	
	private static class CancelBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
}
