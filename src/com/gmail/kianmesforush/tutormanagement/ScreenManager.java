package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScreenManager {
	//The name of the both JFrames
	private static final String FRAME_NAME = "Tutoring Management";
	
	private static Screen currentScreen;
	private static Screen currentPopup;
	
	private static JFrame frame;
	private static JFrame popupFrame;
	
	private static JPanel panel = new JPanel();
	private static JPanel popupPanel = new JPanel();
	
	public static void initialize(Screen currentScreen) {
		//Store arguments
		ScreenManager.currentScreen = currentScreen;
		
		//Initialize a new JFrame that acts as the application's main window
		frame = new JFrame(FRAME_NAME);
		frame.setSize(TutorManagement.SCREEN_WIDTH, TutorManagement.SCREEN_HEIGHT);
		frame.setLocation(TutorManagement.SCREEN_LOC_X, TutorManagement.SCREEN_LOC_Y);
		frame.setContentPane(currentScreen.show(panel));
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
		popupFrame = new JFrame(FRAME_NAME);
		popupFrame.setSize(TutorManagement.POPOUT_WIDTH, TutorManagement.POPOUT_HEIGHT);
		popupFrame.setLocation(TutorManagement.POPOUT_LOC_X, TutorManagement.POPOUT_LOC_Y);
		popupFrame.setVisible(false);
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//Display a new screen by first removing the previous screen and then initializing the next
	public static void setCurrentScreen(Screen screen) {
		// https://stackoverflow.com/questions/21365570/how-to-dispose-a-jpanel-jpanel1-dispose-or-equivalent
		// https://stackoverflow.com/questions/17608421/how-to-reload-a-jpanel
		
		//Hide the pop out frame
		popupFrame.setVisible(false);
		
		//Clean the currentScreen panel and frame
		panel = new JPanel();
		currentScreen = screen;
		frame.setContentPane(currentScreen.show(panel));
		frame.repaint();
		frame.revalidate();
	}
	
	public static void exit() {
		//TODO: handle cleanup code
		
		//Call the exit function of the main program
		TutorManagement.exit();
	}
	
	// Clean old popups and create a new one
	public static void showPopup(Screen popup) {
		popupPanel = new JPanel();
		currentPopup = popup;
		popupFrame.setContentPane(currentPopup.show(popupPanel));
		popupFrame.repaint();
		popupFrame.revalidate();
		popupFrame.setVisible(true);
	}

	// Close the current popup by reloading the current screen which will remove and re-render the panels
	public static void closePopup() {
		popupFrame.setVisible(false);
		setCurrentScreen(currentScreen);
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
}
