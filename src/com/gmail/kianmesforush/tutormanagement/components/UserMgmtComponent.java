package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;

import javax.swing.*;
import java.awt.*;

public class UserMgmtComponent {
	private final User user;
	private final JPanel panel = new JPanel();
	
	private final JLabel nameLabel;
	private final JButton viewNotesBtn = new JButton("View Notes");
	private final JButton editBtn = new JButton("Edit");
	private final JButton removeBtn = new JButton("Remove");
	
	public UserMgmtComponent(User user) {
		this.user = user;
		nameLabel = new JLabel(user.getName());
	}
	public Component show() {
		panel.add(nameLabel);
		panel.add(viewNotesBtn);
		panel.add(editBtn);
		panel.add(removeBtn);
		
		return panel;
	}
}
