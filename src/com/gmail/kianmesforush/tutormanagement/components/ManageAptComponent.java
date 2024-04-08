package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.*;
import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.ManageAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageAptComponent extends ScreenComponent {
	
	private final JLabel numberLabel = new JLabel();
	private final JLabel dateLabel = new JLabel();
	
	private final JButton cancelBtn = new JButton("Cancel");
	private final JButton completedBtn = new JButton("Mark as completed");
	
	private final Appointment apt;
	
	public ManageAptComponent(ArrayList<Appointment> apts, int index) {
		this.apt = apts.get(index);
		
		numberLabel.setText("" + (index + 1));
		dateLabel.setText(apt.getSession().getInfo());
	}
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new GridLayout(1, 4));
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel.setPreferredSize(new Dimension(TutorManagement.SCREEN_WIDTH - 50, 40));
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		dateLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		cancelBtn.addActionListener(new CancelBtnPressed());
		completedBtn.addActionListener(new CompletedBtnPressed());
		
		panel.add(numberLabel);
		panel.add(dateLabel);
		panel.add(cancelBtn);
		panel.add(completedBtn);
		
		StylingManager.stylize(numberLabel, StyleType.PRIMARY);
		StylingManager.stylize(dateLabel, StyleType.PRIMARY);
		StylingManager.stylize(cancelBtn, StyleType.SECONDARY);
		StylingManager.stylize(completedBtn, StyleType.PRIMARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private class CancelBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DataManager.appointments.remove(apt);
			ScreenManager.setCurrentScreen(new ManageAptScreen());
		}
	}
	
	private class CompletedBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DataManager.tutors.forEach(tutor -> {
				if (apt.getTutor().getUUID().equals(tutor.getUUID())) tutor.addHours(apt.getDuration());
			});
			DataManager.tutees.forEach(tutee -> {
				if (apt.getTutee().getUUID().equals(tutee.getUUID())) tutee.addHours(apt.getDuration());
			});
			DataManager.appointments.remove(apt);
			ScreenManager.setCurrentScreen(new ManageAptScreen());
		}
	}
}
