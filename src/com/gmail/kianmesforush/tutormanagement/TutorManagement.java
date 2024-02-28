package com.gmail.kianmesforush.tutormanagement;
//TODO: rename the package to not have any associating characteristics?
import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.datatypes.ClassName;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

public class TutorManagement {
	public static final int SCREEN_WIDTH = 400;
	public static final int SCREEN_HEIGHT = 200;
	public static final int SCREEN_LOC_X = 300;
	public static final int SCREEN_LOC_Y = 300;
	
	public static final int POPOUT_WIDTH = 300;
	public static final int POPOUT_HEIGHT = 400;
	public static final int POPOUT_LOC_X = 200;
	public static final int POPOUT_LOC_Y = 200;
	
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
	
	public static void loadSampleData() {
		DataManager.sessions.add(new TutoringSession("Test Session A"));
		DataManager.sessions.add(new TutoringSession("Test Session B"));
		DataManager.classNames.add(new ClassName("Test Class A"));
		DataManager.classNames.add(new ClassName("Test Class B"));
		DataManager.proficiencies.add(new Proficiency("Test Proficiency A"));
		DataManager.proficiencies.add(new Proficiency("Test Proficiency B"));
		DataManager.skills.add(new Skill("Test Skill A"));
		DataManager.skills.add(new Skill("Test Skill B"));
		
		DataManager.tutors.add(new Tutor("Test Tutor A"));
		DataManager.tutors.add(new Tutor("Test Tutor B"));
		
		//Uses the last added index (the new example tutors)
		DataManager.tutors.get(DataManager.tutors.size() - 1).availability.add(DataManager.sessions.get(DataManager.sessions.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).classNames.add(DataManager.classNames.get(DataManager.classNames.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).proficiencies.add(DataManager.proficiencies.get(DataManager.proficiencies.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).skills.add(DataManager.skills.get(DataManager.skills.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).notes.add("Test Note A");
		DataManager.tutors.get(DataManager.tutors.size() - 1).notes.add("Test Note B");
	}
	
}
