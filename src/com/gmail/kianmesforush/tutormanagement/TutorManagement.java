package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.datatypes.TutoringSession;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.TutorMgmtScreen;

public class TutorManagement {
	public static int SCREEN_WIDTH = 400;
	public static int SCREEN_HEIGHT = 200;
	public static int SCREEN_LOC_X = 300;
	public static int SCREEN_LOC_Y = 300;
	
	public static int POPOUT_WIDTH = 200;
	public static int POPOUT_HEIGHT = 200;
	public static int POPOUT_LOC_X = 200;
	public static int POPOUT_LOC_Y = 200;
	
	
	
	
	public static void main(String[] args) {
		DataManager.initialize();
		//Initialize the ScreenManager which will drive the main function of the app
		ScreenManager.initialize(new HomeScreen());
	}
	
	public static void exit() {
		//TODO:
		DataManager.save();
		System.exit(0);
	}
}
