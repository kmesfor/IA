package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.ScreenComponent;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.datatypes.UserType;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.CreateAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserSearchComponent extends ScreenComponent {
	
	private final JLabel numberLabel = new JLabel();
	private final JLabel nameLabel = new JLabel();
	private final JButton selectBtn = new JButton("Select");
	
	
	private final User user;
	private final JPanel sidePanel = new JPanel(new BorderLayout());
	
	public UserSearchComponent(ArrayList<User> users, User user) {
		this.user = user;
		numberLabel.setText("" + (users.indexOf(user) + 1));
		nameLabel.setText(user.getName());
	}
	
	public JComponent show(JPanel panel) {
		
		panel.setLayout(new GridLayout(1, 2));
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel.setPreferredSize(new Dimension(TutorManagement.POPOUT_WIDTH - 50, 40));
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		sidePanel.add(numberLabel, BorderLayout.WEST);
		
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		sidePanel.add(nameLabel, BorderLayout.CENTER);
		
		panel.add(sidePanel);
		panel.add(selectBtn);
		selectBtn.addActionListener(new SelectBtnPressed());
		
		StylingManager.stylize(numberLabel, StyleType.PRIMARY);
		StylingManager.stylize(nameLabel, StyleType.PRIMARY);
		StylingManager.stylize(selectBtn, StyleType.PRIMARY);
		StylingManager.stylize(sidePanel, StyleType.PRIMARY);
		
		return panel;
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
