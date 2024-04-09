package org.candidates.ibo.kgb197.tutormanagement.datatypes;

import javax.swing.*;

/**
 * An abstract class that serves the purpose of being a model for
 * all other screens displayed throughout the program.
 */
public abstract class Screen {
	//Method to load the panel's content, returns the panel
	public abstract JComponent show(JPanel panel);
	
	/*
	Screen creation style-guide:
		1. Initialize components as fields within the Screen class. Set panel layouts
		during initialization when possible
			1a. Group similar components together in initialization
		2. Set the primary panel layout
		3. Work from top to bottom, left to right creating, stylizing, and adding
		components to subpanels or primary panel
			3a. Create component
			3b. Add action listeners
			3c. Add to subpanels
		4. Add all subpanels to primary panel
		5. Stylize using StylingManager
		6. Initialize data, content, call any other helper methods
		7. Return panel
	 */
}