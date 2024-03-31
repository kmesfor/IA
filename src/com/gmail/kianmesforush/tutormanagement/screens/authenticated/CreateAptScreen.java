package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.popups.SearchPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAptScreen extends Screen {
	private User tutorSelected;
	private User tuteeSelected;
	
	public void setTutorSelected(User tutor) {
		this.tutorSelected = tutor;
		ScreenManager.setCurrentScreen(this);
	}
	
	public void setTuteeSelected(User tutee) {
		this.tuteeSelected = tutee;
		ScreenManager.setCurrentScreen(this);
	}
	
	public JComponent show(JPanel panel) {
		//Components are declared in #show() so refresh occurs properly
		JLabel tutorTextLabel = new JLabel("Tutor: ");
		JLabel tuteeTextLabel = new JLabel("Tutee: ");
		
		JButton backBtn = new JButton("Back");
		JButton selectTutorBtn = new JButton("Select tutor");
		JButton selectTuteeBtn = new JButton("Select tutee");
		
		JPanel btnPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel tutorPanel = new JPanel(new BorderLayout());
		JPanel tuteePanel = new JPanel(new BorderLayout());
		
		panel.setLayout(new BorderLayout());
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		mainPanel.add(tutorPanel);
		mainPanel.add(tuteePanel);
		
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
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}
}
