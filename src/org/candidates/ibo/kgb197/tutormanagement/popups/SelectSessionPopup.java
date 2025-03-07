package org.candidates.ibo.kgb197.tutormanagement.popups;

import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralData;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.ScreenPopup;
import org.candidates.ibo.kgb197.tutormanagement.screens.authenticated.CreateAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectSessionPopup extends ScreenPopup {
	private final ArrayList<GeneralData> sessions;
	
	private final JPanel upperPanel = new JPanel(new GridLayout(0, 2));
	private final JButton backBtn = new JButton("Back");
	
	public SelectSessionPopup(ArrayList<GeneralData> sessions) {
		this.sessions = sessions;
	}
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		
		for (GeneralData session : sessions) {
			JLabel label = new JLabel(session.getInfo());
			upperPanel.add(label);
			JButton button = new JButton("Select session");
			button.addActionListener(new SelectBtnPressed(session));
			StylingManager.stylize(label, StyleType.PRIMARY);
			StylingManager.stylize(button, StyleType.PRIMARY);
			upperPanel.add(button);
		}
		
		backBtn.addActionListener(new BackBtnPressed());
		
		panel.add(upperPanel, BorderLayout.CENTER);
		panel.add(backBtn, BorderLayout.SOUTH);
		
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
	private static class SelectBtnPressed implements ActionListener {
		private final GeneralData session;
		public SelectBtnPressed(GeneralData session) {
			this.session = session;
		}
		
		public void actionPerformed(ActionEvent e) {
			((CreateAptScreen) ScreenManager.getCurrentScreen()).setSessionSelected(session);
		}
	}
}
