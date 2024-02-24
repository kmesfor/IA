package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScreenManager {
	private static Screen currentScreen;
	private static Screen currentPopup;
	
	private static JFrame frame;
	private static JFrame popupFrame;
	
	public static void initialize(Screen currentScreen) {
		//Store arguments
		ScreenManager.currentScreen = currentScreen;
		
		//Initialize a new JFrame that acts as the application's main window
		frame = new JFrame("Tutoring Management");
		frame.setSize(TutorManagement.SCREEN_WIDTH, TutorManagement.SCREEN_HEIGHT);
		frame.setLocation(TutorManagement.SCREEN_LOC_X, TutorManagement.SCREEN_LOC_Y);
		frame.setContentPane(currentScreen.show());
		frame.setVisible(true);
		
		//Ignore close operation to shut down application cleanly instead
		//https://stackoverflow.com/questions/12210972/setdefaultcloseoperation-to-show-a-jframe-instead
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit? Data may not be saved.") == JOptionPane.OK_OPTION) {
					exit();
				}
			}
		});
		
		//Initial a new hidden JFrame that acts as the application's popup window
		popupFrame = new JFrame("Tutoring Management");
		popupFrame.setSize(TutorManagement.POPOUT_WIDTH, TutorManagement.POPOUT_HEIGHT);
		popupFrame.setLocation(TutorManagement.POPOUT_LOC_X, TutorManagement.POPOUT_LOC_Y);
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
		
		//Hide the pop out frame
		popupFrame.setVisible(false);
		
		//Load the screen passed as an argument
		currentScreen = screen;
		frame.setContentPane(screen.show());
	}
	
	public static void exit() {
		//TODO: handle cleanup code
		
		//Call the exit function of the main program
		TutorManagement.exit();
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
