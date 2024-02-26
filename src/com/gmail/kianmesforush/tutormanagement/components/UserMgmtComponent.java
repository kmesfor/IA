package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.Tutee;
import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.TutorMgmtScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserMgmtComponent {
	private final User user;
	private final ArrayList<Tutor> tutors;
	private final JPanel panel = new JPanel();
	
	private final JLabel numberLabel;
	private final JLabel nameLabel;
	private final JButton notesBtn = new JButton("Notes");
	private final JButton editBtn = new JButton("Edit");
	private final JButton removeBtn = new JButton("Remove");
	
	public UserMgmtComponent(ArrayList<Tutor> tutors, int index) {
		this.tutors = tutors;
		this.user = tutors.get(index);
		numberLabel = new JLabel(""+ (index+1));
		nameLabel = new JLabel(user.getName());
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public Component show() {
		removeBtn.addActionListener(new RemoveBtnPressed());
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
		
		return panel;
		
	}
	
	public class RemoveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user instanceof Tutor) tutors.remove(user);
			ScreenManager.setCurrentScreen(new TutorMgmtScreen(tutors));
		}
	}
	
}
