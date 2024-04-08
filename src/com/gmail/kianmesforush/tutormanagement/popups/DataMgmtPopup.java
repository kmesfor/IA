package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;
import com.gmail.kianmesforush.tutormanagement.StylingManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralData;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralDataType;
import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DataMgmtPopup extends Screen {
	private final String addBtnText;
	private final String inputTextFieldText;
	private final String menuLabelText;
	private final ArrayList<GeneralData> dataList;
	private final GeneralDataType type;
	
	private final JPanel upperPanel = new JPanel();
	private final JPanel newDataPanel = new JPanel();
	private final JPanel dataPanel = new JPanel();
	private final JPanel lowerPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton saveBtn = new JButton("Save");
	
	private final JButton addBtn;
	private final JTextField textField;
	private final JLabel dataTypeLabel;
	
	@SuppressWarnings("unchecked")
	public DataMgmtPopup(String addBtnText, String inputTextFieldText, String menuLabelText, ArrayList<GeneralData> dataList, GeneralDataType type) {
		this.addBtnText = addBtnText;
		this.inputTextFieldText = inputTextFieldText;
		this.menuLabelText = menuLabelText;
		// #clone() creates a shallow copy, this is okay for GeneralData which is just text
		// SuppressWarnings for an unchecked cast, GeneralData should never be deep
		this.dataList = (ArrayList<GeneralData>) dataList.clone();
		this.type = type;
		
		addBtn = new JButton(addBtnText);
		textField = new JTextField(inputTextFieldText);
		dataTypeLabel = new JLabel(menuLabelText);
	}
	
	public JComponent show(JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		newDataPanel.setLayout(new BorderLayout());
		
		dataTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(dataTypeLabel, BorderLayout.NORTH);
		
		newDataPanel.add(textField, BorderLayout.CENTER);
		addBtn.addActionListener(new AddBtnPressed());
		newDataPanel.add(addBtn, BorderLayout.EAST);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(newDataPanel, BorderLayout.SOUTH);
		
		
		//Ensures there are at least 10 rows (styling)
		dataPanel.setLayout(new GridLayout(Math.max(dataList.size(), 10), 1));
		for (GeneralData data : dataList) {
			JButton componentBtn = new JButton("X");
			componentBtn.addActionListener(new RemoveBtnPressed(data));
			StylingManager.stylize(componentBtn, StyleType.SECONDARY);
			
			JPanel componentPanel = new JPanel(new BorderLayout());
			componentPanel.add(componentBtn, BorderLayout.WEST);
			
			JLabel componentLabel = new JLabel(data.getInfo());
			componentPanel.add(componentLabel, BorderLayout.CENTER);
			StylingManager.stylize(componentLabel, StyleType.SECONDARY);
			dataPanel.add(componentPanel);
		}
		
		lowerPanel.setLayout(new BorderLayout());
		
		backBtn.addActionListener(new BackBtnPressed());
		lowerPanel.add(backBtn, BorderLayout.WEST);
		
		saveBtn.addActionListener(new SaveBtnPressed());
		lowerPanel.add(saveBtn, BorderLayout.EAST);
		
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(dataPanel, BorderLayout.CENTER);
		panel.add(lowerPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(dataTypeLabel, StyleType.SECONDARY);
		StylingManager.stylize(textField, StyleType.PRIMARY);
		StylingManager.stylize(addBtn, StyleType.PRIMARY);
		StylingManager.stylize(saveBtn, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (type == GeneralDataType.CLASS) {
				DataManager.classNames.forEach(data -> {
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				DataManager.classNames = dataList;
			} else if (type == GeneralDataType.SKILL) {
				DataManager.skills.forEach(data -> {
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				DataManager.skills = dataList;
			} else if (type == GeneralDataType.SESSION) {
				DataManager.sessions.forEach(data -> {
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				DataManager.sessions = dataList;
			} else if (type == GeneralDataType.PROFICIENCY) {
				DataManager.proficiencies.forEach(data -> {
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				DataManager.proficiencies = dataList;
			} else {
				System.out.println("Invalid GeneralData tried to be removed");
			}
			ScreenManager.closePopup();
		}
	}
	
	/**
	 * Remove an existing GeneralData object from the appropriate list
	 */
	private class RemoveBtnPressed implements ActionListener {
		// The GeneralData object to be removed
		private final GeneralData data;
		public RemoveBtnPressed(GeneralData data) {
			this.data = data;
		}
		public void actionPerformed(ActionEvent e) {
			//Remove the object from the appropriate list
			dataList.remove(data);
			//Refresh the displayed screen to update changes
			refresh();
		}
	}
	
	/**
	 * Adds a new GeneralData object to the appropriate list based on text input
	 */
	private class AddBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Add the new object
			dataList.add(new GeneralData(textField.getText(), type));
			//Refresh the displayed screen to update changes
			refresh();
		}
	}
	
	private void refresh() {
		ScreenManager.showPopup(new DataMgmtPopup(addBtnText, inputTextFieldText, menuLabelText, dataList, type));
	}
	
}
