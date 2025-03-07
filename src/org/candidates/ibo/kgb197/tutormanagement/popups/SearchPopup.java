package org.candidates.ibo.kgb197.tutormanagement.popups;

import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.components.UserSearchComponent;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralData;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.ScreenPopup;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.User;
import org.candidates.ibo.kgb197.tutormanagement.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchPopup extends ScreenPopup {
	private final JPanel upperPanel = new JPanel();
	private final JPanel lowerPanel = new JPanel();
	private final JPanel resultsPanel = new JPanel();
	private final JPanel btnPanel = new JPanel();
	private final JTabbedPane pane = new JTabbedPane();
	
	private final JLabel availabilityLabel = new JLabel("Availability:");
	private final JLabel classesLabel = new JLabel("Classes:");
	private final JLabel skillsLabel = new JLabel("Skills:");
	private final JLabel proficienciesLabel = new JLabel("Proficiencies:");
	private final HashMap<GeneralData, JPanel> availabilityCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JPanel> classCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JPanel> skillCheckboxes = new HashMap<>();
	private final HashMap<GeneralData, JPanel> proficiencyCheckboxes = new HashMap<>();
	
	private final ArrayList<GeneralData> filters = new ArrayList<>();
	
	private final JButton backBtn = new JButton("Back");
	
	private final ArrayList<User> users = new ArrayList<>();
	
	public SearchPopup(ArrayList<User> users) {
		//Creates a deep clone array
		users.forEach((User user) -> this.users.add(new User(user)));
	}
	
	public JComponent show(JPanel panel) {
		upperPanel.setLayout(new GridLayout(0, 1));
		populateCheckboxes();
		
		lowerPanel.setLayout(new BorderLayout());
		lowerPanel.add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
		lowerPanel.add(btnPanel, BorderLayout.SOUTH);
		
		
		btnPanel.add(backBtn);
		backBtn.addActionListener(new BackBtnPressed());
		
		panel.setLayout(new BorderLayout());
		pane.add("Filters", new JScrollPane(upperPanel));
		pane.add("Results", new JScrollPane(resultsPanel));
		panel.add(pane, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(availabilityLabel, StyleType.PRIMARY);
		StylingManager.stylize(classesLabel, StyleType.PRIMARY);
		StylingManager.stylize(skillsLabel, StyleType.PRIMARY);
		StylingManager.stylize(proficienciesLabel, StyleType.PRIMARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		loadUsers();
		
		return panel;
	}
	
	private JPanel checkbox(GeneralData data) {
		JPanel panel = new JPanel();
		JCheckBox checkBox = new JCheckBox(data.getInfo());
		checkBox.addActionListener(new FilterUpdated(data, checkBox));
		checkBox.setEnabled(true);
		panel.add(checkBox);
		StylingManager.stylize(checkBox, StyleType.SECONDARY);
		return panel;
	}
	
	private void populateCheckboxes() {
		upperPanel.add(availabilityLabel);
		for (GeneralData session : DataManager.sessions) {
			availabilityCheckboxes.put(session, checkbox(session));
			upperPanel.add(availabilityCheckboxes.get(session));
		}
		upperPanel.add(classesLabel);
		for (GeneralData className : DataManager.classNames) {
			classCheckboxes.put(className, checkbox(className));
			upperPanel.add(classCheckboxes.get(className));
		}
		upperPanel.add(skillsLabel);
		for (GeneralData skill : DataManager.skills) {
			skillCheckboxes.put(skill, checkbox(skill));
			upperPanel.add(skillCheckboxes.get(skill));
		}
		upperPanel.add(proficienciesLabel);
		for (GeneralData proficiency : DataManager.proficiencies) {
			proficiencyCheckboxes.put(proficiency, checkbox(proficiency));
			upperPanel.add(proficiencyCheckboxes.get(proficiency));
		}
	}
	
	/**
	 * Loops through each user and adds them to the filteredUsers list if
	 * they are included within the designated filters. filteredUsers will be
	 * displayed in the resultsPanel of the screen
	 * @return the ArrayList of filteredUsers
	 */
	private ArrayList<User> getFilteredUsers() {
		ArrayList<User> filteredUsers = new ArrayList<>();
		users.forEach((User user) -> {
			if (user.isIncluded(filters)) filteredUsers.add(new User(user));
		});
		return filteredUsers;
	}
	
	/**
	 * Populate the resultsPanel with all users that are included in the filters list
	 */
	private void loadUsers() {
		ArrayList<User> filteredUsers = getFilteredUsers();
		resultsPanel.removeAll();
		resultsPanel.setLayout(new GridLayout(filteredUsers.size(), 1));
		for (User user : filteredUsers) {
			resultsPanel.add(new UserSearchComponent(filteredUsers, user).show(new JPanel()));
		}
		pane.setTitleAt(1, "Results (" + filteredUsers.size() + ")");
		if (filteredUsers.isEmpty()) {
			JLabel label = new JLabel("No users found. Try reducing filters");
			label.setForeground(Color.red);
			resultsPanel.add(label);
		}
	}
	
	/**
	 * Update a filter's status when toggled
	 */
	private class FilterUpdated implements ActionListener {
		 // The filter
		private final GeneralData filter;
		 // The checkbox to toggle the filter's status
		private final JCheckBox checkbox;
		
		public FilterUpdated(GeneralData filter, JCheckBox checkbox) {
			this.filter = filter;
			this.checkbox = checkbox;
		}
		
		//Called when a checkbox is toggled
		public void actionPerformed(ActionEvent e) {
			//If the checkbox is set to active
			if (checkbox.isSelected()) {
				//Add the filter to the list of filters
				filters.add(filter);
			} else {
				//Remove the filter from the list of filters
				filters.remove(filter);
			}
			//Reload the filtered users based on updates to the filter list
			loadUsers();
			//Reload the JPanel contents
			upperPanel.repaint();
			upperPanel.revalidate();
		}
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) { ScreenManager.closePopup(); }
	}
}
