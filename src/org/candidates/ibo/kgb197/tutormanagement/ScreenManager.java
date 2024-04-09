package org.candidates.ibo.kgb197.tutormanagement;

import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The static management class for the program's screens.
 * Handles the rendering and displaying of screens and popups
 */
public class ScreenManager {
	/**
	 * Constant for the name of the JFrame panel and popup.
	 */
	private static final String FRAME_NAME = "Tutoring Management";
	
	/**
	 * The currently displayed screen. Used to refresh the main screen after closing a popup.
	 * Also used in {@link ScreenManager#getCurrentScreen()}
	 *
	 * Note: a currentPopup variable is redundant in current use case because
	 * popups are not refreshed. However, to add this functionality, a similar methodology
	 * with currentScreen and {@link ScreenManager#closePopup()} could be used.
	 */
	private static Screen currentScreen;
	
	/**
	 * The JFrame holding the current JPanel. Frame is reused throughout program's lifecycle
	 */
	private static JFrame frame;
	/**
	 * The JFrame holding the current popup (if active). Frame is reused throughout program's lifecycle
	 */
	private static JFrame popupFrame;
	
	
	/**
	 * Initializes the ScreenManager's main frame and popout frame
	 * and shows the program's first screen.
	 *
	 * @param currentScreen the current screen to be shown
	 */
	public static void initialize(Screen currentScreen) {
		//Store arguments
		ScreenManager.currentScreen = currentScreen;
		
		//Initialize a new JFrame that acts as the application's main window
		frame = new JFrame(FRAME_NAME);
		frame.setSize(TutorManagement.SCREEN_WIDTH, TutorManagement.SCREEN_HEIGHT);
		frame.setLocation(TutorManagement.SCREEN_LOC_X, TutorManagement.SCREEN_LOC_Y);
		frame.setContentPane(currentScreen.show(new JPanel()));
		frame.setVisible(true);
		
		//Ignore close operation to shut down application cleanly instead
		//Source: https://stackoverflow.com/questions/12210972/setdefaultcloseoperation-to-show-a-jframe-instead
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				//Saves data before exit
				exit();
			}
		});
		
		//Initial a new hidden JFrame that acts as the application's popup window
		popupFrame = new JFrame(FRAME_NAME);
		popupFrame.setSize(TutorManagement.POPOUT_WIDTH, TutorManagement.POPOUT_HEIGHT);
		popupFrame.setLocation(TutorManagement.POPOUT_LOC_X, TutorManagement.POPOUT_LOC_Y);
		popupFrame.setVisible(false);
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Displays a new screen by clearing the previous frame and initializing the next.
	 * Also removes the outstanding popup (if applicable).
	 *
	 * @param screen the screen to be shown
	 */
	public static void setCurrentScreen(Screen screen) {
		// Source: https://stackoverflow.com/questions/21365570/how-to-dispose-a-jpanel-jpanel1-dispose-or-equivalent
		// Source: https://stackoverflow.com/questions/17608421/how-to-reload-a-jpanel
		
		//Hide the pop out frame
		popupFrame.setVisible(false);
		
		//Set the screen
		currentScreen = screen;
		frame.setContentPane(currentScreen.show(new JPanel()));
		//Clean the currentScreen panel and frame and repaint
		frame.repaint();
		frame.revalidate();
	}
	
	/**
	 * Exits the ScreenManager and calls {@link TutorManagement#exit()}
	 */
	public static void exit() {
		//TODO: handle cleanup code
		
		//Call the exit function of the main program
		TutorManagement.exit();
	}
	
	/**
	 * Show a new popup. Cleans the old popup (if applicable)
	 * and establishes a new one
	 *
	 * @param popup the popup
	 */
	public static void showPopup(Screen popup) {
		//Set the popupFrame to the new Popup
		popupFrame.setContentPane(popup.show(new JPanel()));
		//Re-render the frame
		popupFrame.repaint();
		popupFrame.revalidate();
		//Make the frame visible
		popupFrame.setVisible(true);
	}
	
	/**
	 * Close the current popup by reloading the current screen. This will re-render the currentScreen
	 */
	public static void closePopup() {
		popupFrame.setVisible(false);
		setCurrentScreen(currentScreen);
	}
	
	/**
	 * Gets current screen.
	 *
	 * @return the current screen
	 */
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
}
