package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
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

public class UserMgmtComponent {
	private final User user;
	private final ArrayList<User> users;
	public final JPanel panel = new JPanel();
	
	public UserMgmtComponent(ArrayList<User> users, int index) {
		//TODO: change this to handle tutees as well
		this.users = users;
		this.user = users.get(index);
		
		JLabel numberLabel = new JLabel("" + (index + 1));
		JLabel nameLabel = new JLabel(user.getName());
		JButton notesBtn = new JButton("Notes");
		//TODO: is the edit btn going to have a problem bc user elements are passed by reference?
		JButton editBtn = new JButton("Edit");
		JButton removeBtn = new JButton("Remove");
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		removeBtn.addActionListener(new RemoveBtnPressed());
		editBtn.addActionListener(new EditBtnPressed());
		notesBtn.addActionListener(new NotesBtnPressed());
		
		panel.setLayout(new GridLayout(1, 5));
		panel.add(numberLabel);
		panel.add(nameLabel);
		panel.add(notesBtn);
		panel.add(editBtn);
		panel.add(removeBtn);
		
		//https://stackoverflow.com/questions/2174319/is-it-possible-to-have-a-java-swing-border-only-on-the-top-side
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		//https://stackoverflow.com/questions/22920046/how-to-set-fix-size-of-jlabel
		panel.setPreferredSize(new Dimension(TutorManagement.SCREEN_WIDTH - 50, 40));
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
