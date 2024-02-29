package com.gmail.kianmesforush.tutormanagement.datatypes;

import javax.swing.*;

public abstract class Screen {
	//Method to load the panel's content, returns the panel
	public abstract JComponent show(JPanel panel);
}