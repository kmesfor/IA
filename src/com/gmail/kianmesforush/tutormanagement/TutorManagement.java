package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.TutoringSession;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

public class TutorManagement {
	public static void main(String[] args) {
		//Initialize the ScreenManager which will drive the main function of the app
		ScreenManager.initialize(new HomeScreen());
		
		DataManager.initialize();
		DataManager.sessions.add(new TutoringSession("test"));
	}
	
	public static void exit() {
		//TODO:
		DataManager.save();
		System.exit(0);
	}
}
