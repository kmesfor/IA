package com.gmail.kianmesforush.tutormanagement.components;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.TutorManagement;
import com.gmail.kianmesforush.tutormanagement.datatypes.Appointment;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralData;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.ManageAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageAptComponent {
	private final ArrayList<Appointment> apts;
	private final Appointment apt;
	public final JPanel panel = new JPanel();
	
	public ManageAptComponent(ArrayList<Appointment> apts, int index) {
		this.apts = apts;
		this.apt = apts.get(index);
		
		JLabel numberLabel = new JLabel("" + (index + 1));
		JLabel dateLabel = new JLabel(apt.getSession().getInfo());
		
		JButton cancelBtn = new JButton("Cancel");
		JButton completedBtn = new JButton("Mark as completed");
		
		cancelBtn.addActionListener(new CancelBtnPressed());
		completedBtn.addActionListener(new CompletedBtnPressed());
		
		numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		dateLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		panel.setLayout(new GridLayout(1, 4));
		panel.add(numberLabel);
		panel.add(dateLabel);
		panel.add(cancelBtn);
		panel.add(completedBtn);
		
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel.setPreferredSize(new Dimension(TutorManagement.SCREEN_WIDTH - 50, 40));
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
