package com.gmail.kianmesforush.tutormanagement;

import javax.swing.*;
import java.awt.*;

public interface Screen {
	//The JPanel within the main JFrame of the app
	public JPanel panel = new JPanel();
	//Method to load the panel's content, returns the panel
	public JComponent show();
}
