package org.candidates.ibo.kgb197.tutormanagement.components;

import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.TutorManagement;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.ScreenComponent;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.UserType;
import org.candidates.ibo.kgb197.tutormanagement.screens.authenticated.CreateAptScreen;

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
	
	/**
	 * A component displaying each result of filtering users
	 * @param users the list of users that have been filtered
	 * @param user the user to create a component for
	 */
	public UserSearchComponent(ArrayList<User> users, User user) {
		this.user = user;
		numberLabel.setText("" + (users.indexOf(user) + 1));
		nameLabel.setText(user.getName());
	}
	
	/**
	 * Display the UserSearchComponent for a User object
	 * @param panel the JPanel to display components on
	 * @return a JComponent containing the Screen's rendered components
	 */
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
