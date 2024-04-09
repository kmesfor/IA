package org.candidates.ibo.kgb197.tutormanagement.components;

import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.TutorManagement;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.ScreenComponent;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.UserType;
import org.candidates.ibo.kgb197.tutormanagement.popups.EditUserPopup;
import org.candidates.ibo.kgb197.tutormanagement.popups.NotesPopup;
import org.candidates.ibo.kgb197.tutormanagement.screens.authenticated.UserMgmtScreen;

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
	
	/**
	 * A component that allows the user to manage a tutor or tutee
	 * @param users the list of users (tutors or tutees)
	 * @param index the index of the user to manage
	 */
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
		
		//Source: https://stackoverflow.com/questions/22920046/how-to-set-fix-size-of-jlabel
		panel.setPreferredSize(new Dimension(TutorManagement.SCREEN_WIDTH - 50, 40));
		return panel;
	}
	
	/**
	 * Removes a User object from a list of tutors or tutees depending on its type
	 */
	private class RemoveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Remove from the list, does not remove from DataManager until action is saved on a different screen
			users.remove(user);
			//Displays a UserMgmtScreen for tutors or tutees depending on the deleted user's type
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
