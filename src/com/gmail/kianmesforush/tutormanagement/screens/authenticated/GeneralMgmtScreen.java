package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralMgmtScreen extends Screen {
	private final JButton backBtn = new JButton("Back");
	private final JButton editClassesBtn = new JButton("Edit Classes");
	private final JButton editSkillsBtn = new JButton("Edit Skills");
	private final JButton editProficienciesBtn = new JButton("Edit Proficiencies");
	private final JButton editSessionsBtn = new JButton("Edit Sessions");
	
	private final JPanel btnsPanel = new JPanel();
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		btnsPanel.setLayout(new GridLayout(4, 1));
		
		btnsPanel.add(editClassesBtn);
		btnsPanel.add(editSkillsBtn);
		btnsPanel.add(editProficienciesBtn);
		btnsPanel.add(editSessionsBtn);
		
		panel.add(btnsPanel, BorderLayout.CENTER);
		
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.setCurrentScreen(new AuthUserPrimaryScreen()); }
	}
}
