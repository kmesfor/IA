package com.gmail.kianmesforush.tutormanagement.screens.authenticated;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralData;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralDataType;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;
import com.gmail.kianmesforush.tutormanagement.popups.DataMgmtPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		
		editClassesBtn.addActionListener(new EditClassesPressed());
		editSkillsBtn.addActionListener(new EditSkillsPressed());
		editProficienciesBtn.addActionListener(new EditProficienciesPressed());
		editSessionsBtn.addActionListener(new EditSessionsPressed());
		
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
	
	private static class EditClassesPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.showPopup(new DataMgmtPopup("Add class", "Class name", "Edit classes", DataManager.classNames, GeneralDataType.CLASS));}
	}
	private static class EditSkillsPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.showPopup(new DataMgmtPopup("Add skill", "Skill name", "Edit skills", DataManager.skills, GeneralDataType.SKILL));}
	}
	
	private static class EditProficienciesPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.showPopup(new DataMgmtPopup("Add proficiency", "Proficiency name", "Edit proficiencies", DataManager.proficiencies, GeneralDataType.PROFICIENCY));}
	}
	
	private static class EditSessionsPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.showPopup(new DataMgmtPopup("Add session", "Session name", "Edit sessions", DataManager.sessions, GeneralDataType.SESSION));}
	}
	
}
