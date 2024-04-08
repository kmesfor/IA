package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.ScreenComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.popups.EditUserPopup;
import com.gmail.kianmesforush.tutormanagement.popups.NotesPopup;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.UserMgmtScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserMgmtComponent extends ScreenComponent {
	private final JLabel numberLabel = new JLabel();
	private final JLabel nameLabel = new JLabel();
	
	private final JButton notesBtn = new JButton("Notes");
	private final JButton editBtn = new JButton("Edit");
	private final JButton removeBtn = new JButton("Remove");
	
	private final User user;
	private final ArrayList<User> users;
	
	public UserMgmtComponent(ArrayList<User> users, int index) {
		this.users = users;
		this.user = users.get(index);
		numberLabel.setText("" + (index + 1));
		nameLabel.setText(user.getName());
	}
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new GridLayout(1, 5));
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, StylingManager.StylePreset.GRAY));
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(numberLabel);
		
		nameLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, StylingManager.StylePreset.GRAY));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(nameLabel);
		
		notesBtn.addActionListener(new NotesBtnPressed());
		panel.add(notesBtn);
		
		editBtn.addActionListener(new EditBtnPressed());
		panel.add(editBtn);
		
		removeBtn.addActionListener(new RemoveBtnPressed());
		panel.add(removeBtn);
		
		StylingManager.stylize(numberLabel, StyleType.SECONDARY);
		StylingManager.stylize(nameLabel, StyleType.PRIMARY);
		StylingManager.stylize(notesBtn, StyleType.PRIMARY);
		StylingManager.stylize(editBtn, StyleType.PRIMARY);
		StylingManager.stylize(removeBtn, StyleType.SECONDARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		//https://stackoverflow.com/questions/22920046/how-to-set-fix-size-of-jlabel
		panel.setPreferredSize(new Dimension(TutorManagement.SCREEN_WIDTH - 50, 40));
		return panel;
	}
	
	private class RemoveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			users.remove(user);
			if (user.getType() == UserType.TUTOR) ScreenManager.setCurrentScreen(new UserMgmtScreen(users, UserType.TUTOR));
			else ScreenManager.setCurrentScreen(new UserMgmtScreen(users, UserType.TUTEE));
		}
	}
	
	private class EditBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user.getType() == UserType.TUTOR) ScreenManager.showPopup(new EditUserPopup(UserType.TUTOR, user));
			else ScreenManager.showPopup(new EditUserPopup(UserType.TUTEE, user));
		}
	}
	
	private class NotesBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new NotesPopup(user));
		}
	}
	
}
