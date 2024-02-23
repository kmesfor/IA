package com.gmail.kianmesforush.tutormanagement.datatypes;

import javax.swing.*;

public interface Screen {
	//The JPanel within the main JFrame of the app
	JPanel panel = new JPanel();
	//Method to load the panel's content, returns the panel
	JComponent show();
}
