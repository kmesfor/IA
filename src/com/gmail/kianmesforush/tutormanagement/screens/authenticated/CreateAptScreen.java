package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Appointment;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralData;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.popups.SearchPopup;
import com.gmail.kianmesforush.tutormanagement.popups.SelectSessionPopup;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAptScreen extends Screen {
	private User tutorSelected;
	private User tuteeSelected;
	private GeneralData sessionSelected;
	private int duration = 1;
	
	public void setTutorSelected(User tutor) {
		this.tutorSelected = tutor;
		ScreenManager.setCurrentScreen(this);
	}
	
	public void setTuteeSelected(User tutee) {
		this.tuteeSelected = tutee;
		ScreenManager.setCurrentScreen(this);
	}
	
	public void setSessionSelected(GeneralData session) {
		this.sessionSelected = session;
		ScreenManager.setCurrentScreen(this);
	}
	
	
	//spinner: https://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html#change
	public JComponent show(JPanel panel) {
		//Components are declared in #show() so refresh occurs properly
		JLabel tutorTextLabel = new JLabel("Tutor: ");
		JLabel tuteeTextLabel = new JLabel("Tutee: ");
		JLabel sessionTextLabel = new JLabel("Session: ");
		JLabel sessionDurationLabel = new JLabel("Duration (hours): ");
		
		JButton backBtn = new JButton("Back");
		JButton selectTutorBtn = new JButton("Select tutor");
		JButton selectTuteeBtn = new JButton("Select tutee");
		JButton selectSessionBtn = new JButton("Select session");
		JSpinner sessionInput = new JSpinner();
		JButton createBtn = new JButton("Create Appointment");
		
		JPanel btnPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel tutorPanel = new JPanel(new BorderLayout());
		JPanel tuteePanel = new JPanel(new BorderLayout());
		JPanel sessionPanel = new JPanel(new BorderLayout());
		JPanel sessionDurationPanel = new JPanel(new BorderLayout());
		
		panel.setLayout(new BorderLayout());
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		mainPanel.add(tutorPanel);
		mainPanel.add(tuteePanel);
		mainPanel.add(sessionPanel);
		mainPanel.add(sessionDurationPanel);
		
		tutorPanel.add(tutorTextLabel, BorderLayout.WEST);
		if (tutorSelected != null) {
			tutorPanel.add(new JLabel(tutorSelected.getName()), BorderLayout.CENTER);
		} else {
			tutorPanel.add(selectTutorBtn, BorderLayout.CENTER);
			selectTutorBtn.addActionListener(new SelectTutorBtnPressed());
		}
		
		tuteePanel.add(tuteeTextLabel, BorderLayout.WEST);
		if (tuteeSelected != null) {
			tuteePanel.add(new JLabel(tuteeSelected.getName()), BorderLayout.CENTER);
		} else {
			tuteePanel.add(selectTuteeBtn, BorderLayout.CENTER);
			selectTuteeBtn.addActionListener(new SelectTuteeBtnPressed());
		}
		
		sessionPanel.add(sessionTextLabel, BorderLayout.WEST);
		if (sessionSelected != null) {
			sessionPanel.add(new JLabel(sessionSelected.getInfo()), BorderLayout.CENTER);
		} else {
			sessionPanel.add(selectSessionBtn, BorderLayout.CENTER);
			selectSessionBtn.addActionListener(new SelectSessionBtnPressed());
		}
		
		sessionDurationPanel.add(sessionDurationLabel, BorderLayout.WEST);
		sessionInput.setModel(new SpinnerNumberModel(duration, 0, Integer.MAX_VALUE, 1));
		sessionInput.addChangeListener(new SessionInputChanged(sessionInput, this));
		sessionDurationPanel.add(sessionInput, BorderLayout.CENTER);
		
		
		btnPanel.add(createBtn);
		createBtn.addActionListener(new CreateAptBtnPressed());
		createBtn.setEnabled(tutorSelected != null && tuteeSelected != null && sessionSelected != null);
		btnPanel.add(backBtn);
		backBtn.addActionListener(new BackBtnPressed());
		
		return panel;
	}
	
	private static class SelectTutorBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new SearchPopup(DataManager.tutors));
		}
	}
	
	private static class SelectTuteeBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new SearchPopup(DataManager.tutees));
		}
	}
	
	private static class SelectSessionBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.showPopup(new SelectSessionPopup(DataManager.sessions));
		}
	}
	
	private class CreateAptBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DataManager.appointments.add(new Appointment(tutorSelected, tuteeSelected, sessionSelected, duration));
			ScreenManager.setCurrentScreen(new ManageAptScreen());
		}
	}
	
	private class SessionInputChanged implements ChangeListener {
		private final JSpinner input;
		private final CreateAptScreen screen;
		public SessionInputChanged(JSpinner input, CreateAptScreen screen) {
			this.input = input;
			this.screen = screen;
		}
		public void stateChanged(ChangeEvent e) {
			duration = ((int) input.getValue());
			ScreenManager.setCurrentScreen(screen);
		}
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new ManageAptScreen()); }
	}
}
