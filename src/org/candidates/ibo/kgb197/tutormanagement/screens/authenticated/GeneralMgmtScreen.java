package org.candidates.ibo.kgb197.tutormanagement.screens.authenticated;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralDataType;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;
import org.candidates.ibo.kgb197.tutormanagement.popups.DataMgmtPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralMgmtScreen extends Screen {
	private final JButton editClassesBtn = new JButton("Edit Classes");
	private final JButton editSkillsBtn = new JButton("Edit Skills");
	private final JButton editProficienciesBtn = new JButton("Edit Proficiencies");
	private final JButton editSessionsBtn = new JButton("Edit Sessions");
	private final JButton backBtn = new JButton("Back");
	
	private final JPanel btnsPanel = new JPanel();
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		btnsPanel.setLayout(new GridLayout(4, 1));
		
		editClassesBtn.addActionListener(new EditClassesPressed());
		btnsPanel.add(editClassesBtn);
		
		editSkillsBtn.addActionListener(new EditSkillsPressed());
		btnsPanel.add(editSkillsBtn);
		
		editProficienciesBtn.addActionListener(new EditProficienciesPressed());
		btnsPanel.add(editProficienciesBtn);
		
		editSessionsBtn.addActionListener(new EditSessionsPressed());
		btnsPanel.add(editSessionsBtn);
		
		panel.add(btnsPanel, BorderLayout.CENTER);
		
		backBtn.addActionListener(new BackBtnPressed());
		panel.add(backBtn, BorderLayout.SOUTH);
		
		StylingManager.stylize(editClassesBtn, StyleType.PRIMARY);
		StylingManager.stylize(editSkillsBtn, StyleType.PRIMARY);
		StylingManager.stylize(editProficienciesBtn, StyleType.PRIMARY);
		StylingManager.stylize(editSessionsBtn, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		
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
