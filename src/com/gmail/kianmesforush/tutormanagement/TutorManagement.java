package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

public class TutorManagement {
	public static void main(String[] args) {
		//Initialize the ScreenManager which will drive the main function of the app
		ScreenManager.initialize(new HomeScreen());
	}
}
