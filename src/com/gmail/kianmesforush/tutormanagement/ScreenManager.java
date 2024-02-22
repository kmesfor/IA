package com.gmail.kianmesforush.tutormanagement;

import javax.swing.*;
import java.awt.*;

public class ScreenManager {
	private static Screen currentScreen;
	private static Screen currentPopup;
	
	private static JFrame frame;
	private static JFrame popupFrame;
	
	public static void initialize(Screen currentScreen) {
		//Store arguments
		ScreenManager.currentScreen = currentScreen;
		
		//Initialize a new JFrame that acts as the application's main window
		//TODO: update sizing & make it a config option
		frame = new JFrame("Tutoring Management");
		frame.setSize(400, 200);
		frame.setLocation(300, 300);
		frame.setContentPane(currentScreen.show());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initial a new hidden JFrame that acts as the application's popup window
		popupFrame = new JFrame("Tutoring Management");
		popupFrame.setSize(200, 200);
		popupFrame.setLocation(200, 200);
		popupFrame.setVisible(false);
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//Display a new screen by first removing the previous screen and then initializing the next
	public static void setCurrentScreen(Screen screen) {
		// https://stackoverflow.com/questions/21365570/how-to-dispose-a-jpanel-jpanel1-dispose-or-equivalent
		// https://stackoverflow.com/questions/17608421/how-to-reload-a-jpanel
		
		//Clean the currentScreen panel and frame
		currentScreen.panel.removeAll();
		currentScreen.panel.setLayout(new FlowLayout());
		frame.revalidate();
		frame.repaint();
		
		//Hide the popoutFrame
		popupFrame.setVisible(false);
		
		//Load the screen passed as an argument
		currentScreen = screen;
		frame.setContentPane(screen.show());
	}
	
	public static void exit() {
		//TODO: handle cleanup code
		System.exit(0);
	}
	
	// Clean old popups and create a new one
	public static void showPopup(Screen popup) {
		currentPopup.panel.removeAll();
		currentPopup.panel.setLayout(new FlowLayout());
		popupFrame.setVisible(true);
		
		currentPopup = popup;
		popupFrame.setContentPane(popup.show());
	}

	// Close the current popup by reloading the current screen which will remove and re-render the panels
	public static void closePopup() {
		setCurrentScreen(currentScreen);
	}
}
