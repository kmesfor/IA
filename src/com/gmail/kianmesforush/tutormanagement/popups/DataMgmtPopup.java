package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.DataManager;
import com.gmail.kianmesforush.tutormanagement.ScreenManager;
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
		newDataPanel.setLayout(new BorderLayout());
		newDataPanel.add(textField, BorderLayout.CENTER);
		addBtn.addActionListener(new AddBtnPressed());
		newDataPanel.add(addBtn, BorderLayout.EAST);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(newDataPanel, BorderLayout.SOUTH);
		
		dataTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(dataTypeLabel, BorderLayout.NORTH);
		
		//Ensures there are at least 10 rows (styling)
		dataPanel.setLayout(new GridLayout(Math.max(dataList.size(), 10), 1));
		for (GeneralData data : dataList) {
			JButton componentBtn = new JButton("X");
			componentBtn.addActionListener(new RemoveBtnPressed(data));
			
			JPanel componentPanel = new JPanel(new BorderLayout());
			componentPanel.add(componentBtn, BorderLayout.WEST);
			componentPanel.add(new JLabel(data.getInfo()), BorderLayout.CENTER);
			dataPanel.add(componentPanel);
		}
		
		lowerPanel.setLayout(new BorderLayout());
		saveBtn.addActionListener(new SaveBtnPressed());
		lowerPanel.add(saveBtn, BorderLayout.WEST);
		
		backBtn.addActionListener(new BackBtnPressed());
		lowerPanel.add(backBtn, BorderLayout.EAST);
		
		panel.setLayout(new BorderLayout());
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(dataPanel, BorderLayout.CENTER);
		panel.add(lowerPanel, BorderLayout.SOUTH);
		
		return panel;
	}
	
	//DOES NOT WORK
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (type) {
				case CLASS -> DataManager.classNames = dataList;
				case SKILL -> DataManager.skills = dataList;
				case SESSION -> DataManager.sessions = dataList;
				case PROFICIENCY -> DataManager.proficiencies = dataList;
			}
			ScreenManager.closePopup();
		}
	}
	
	private class RemoveBtnPressed implements ActionListener {
		private final GeneralData data;
		public RemoveBtnPressed(GeneralData data) {
			this.data = data;
		}
		public void actionPerformed(ActionEvent e) {
			dataList.remove(data);
			refresh();
		}
	}
	
	private class AddBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dataList.add(new GeneralData(textField.getText(), type));
			refresh();
		}
	}
	
	private void refresh() {
		ScreenManager.showPopup(new DataMgmtPopup(addBtnText, inputTextFieldText, menuLabelText, dataList, type));
	}
	
}
