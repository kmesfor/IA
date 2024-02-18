package com.gmail.kianmesforush.tutormanagement;

import javax.swing.*;
import java.awt.*;

public class ScreenManager extends JFrame {
	private static Screen currentScreen;
	private static JFrame frame;
	public static void initialize(Screen currentScreen) {
		//Store arguments
		ScreenManager.currentScreen = currentScreen;
		
		//Initialize a new JFrame that acts as the application's main window
		frame = new JFrame("Tutoring Management");
		frame.setSize(400, 200);
		frame.setLocation(300, 300);
		frame.setContentPane(currentScreen.show());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Display a new screen by first removing the previous screen and then initializing the next
	public static void setCurrentScreen(Screen screen) {
		// https://stackoverflow.com/questions/21365570/how-to-dispose-a-jpanel-jpanel1-dispose-or-equivalent
		currentScreen.panel.removeAll();
		currentScreen.panel.setLayout(new FlowLayout());
		currentScreen = screen;
		frame.setContentPane(screen.show());
	}
	
	public static void exit() {
		//TODO: cleanup
		System.exit(0);
	}
}
