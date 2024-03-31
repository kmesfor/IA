package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.CreateAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserSearchComponent {
	private final User user;
	private final JPanel sidePanel = new JPanel(new BorderLayout());
	
	public final JPanel panel = new JPanel();
	
	public UserSearchComponent(ArrayList<User> users, User user) {
		this.user = user;
		
		JLabel numberLabel = new JLabel("" + (users.indexOf(user) + 1));
		JLabel nameLabel = new JLabel(user.getName());
		JButton selectBtn = new JButton("Select");
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		panel.setLayout(new GridLayout(1, 2));
		panel.add(sidePanel);
		panel.add(selectBtn);
		
		sidePanel.add(numberLabel, BorderLayout.WEST);
		sidePanel.add(nameLabel, BorderLayout.CENTER);
		
		selectBtn.addActionListener(new SelectBtnPressed());
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel.setPreferredSize(new Dimension(TutorManagement.POPOUT_WIDTH - 50, 40));
	}
	
	private class SelectBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ScreenManager.getCurrentScreen() instanceof CreateAptScreen) {
				if (user.getType() == UserType.TUTOR) {
					((CreateAptScreen) ScreenManager.getCurrentScreen()).setTutorSelected(user);
				} else {
					((CreateAptScreen) ScreenManager.getCurrentScreen()).setTuteeSelected(user);
				}
			}
		}
	}
}
