package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.ScreenPopup;
import com.gmail.kianmesforush.tutormanagement.datatypes.User;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.UserMgmtScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotesPopup extends ScreenPopup {
	private final JButton addNoteBtn = new JButton("Add Note");
	private final JButton backBtn = new JButton("Back");
	private final JTextField noteField = new JTextField("New note");
	
	private final JPanel contentPanel = new JPanel();
	private final JPanel newNotePanel = new JPanel();
	
	private final User user;
	
	public NotesPopup(User user) {
		this.user = user;
	}
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		addNoteBtn.addActionListener(new AddNoteBtnPressed());
		newNotePanel.setLayout(new BorderLayout());
		newNotePanel.add(noteField, BorderLayout.CENTER);
		newNotePanel.add(addNoteBtn, BorderLayout.EAST);
		
		//Ensures there are at least 10 rows (to make notes not take up the entire screen)
		contentPanel.setLayout(new GridLayout(Math.max(user.notes.size(), 10), 1));
		for (String note : user.notes) {
			JButton componentBtn = new JButton("X");
			componentBtn.addActionListener(new RemoveNoteBtnPressed(note));
			
			JPanel componentPanel = new JPanel(new BorderLayout());
			JLabel noteLabel = new JLabel(note);
			componentPanel.add(componentBtn, BorderLayout.WEST);
			componentPanel.add(noteLabel, BorderLayout.CENTER);
			contentPanel.add(componentPanel);
			
			StylingManager.stylize(componentBtn, StyleType.SECONDARY);
			StylingManager.stylize(noteLabel, StyleType.SECONDARY);
		}
		
		panel.add(newNotePanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
		
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		StylingManager.stylize(addNoteBtn, StyleType.PRIMARY);
		StylingManager.stylize(noteField, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(contentPanel, StyleType.PRIMARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ScreenManager.getCurrentScreen() instanceof UserMgmtScreen) {
				UserMgmtScreen screen = (UserMgmtScreen) ScreenManager.getCurrentScreen();
				ArrayList<User> data = screen.getData();
				ScreenManager.setCurrentScreen(new UserMgmtScreen(data, screen.getType()));
			}
		}
	}
	
	private class AddNoteBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.notes.add(noteField.getText());
			ScreenManager.showPopup(new NotesPopup(user));
		}
	}
	
	private class RemoveNoteBtnPressed implements ActionListener {
		private final String note;
		public RemoveNoteBtnPressed(String note) {
			this.note = note;
		}
		public void actionPerformed(ActionEvent e) {
			user.notes.remove(note);
			ScreenManager.showPopup(new NotesPopup(user));
		}
	}
}
