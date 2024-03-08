package com.gmail.kianmesforush.tutormanagement;
//TODO: rename the package to not have any associating characteristics?
import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.datatypes.ClassName;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

/**
 * The main class of the program.
 */
public class TutorManagement {
	/**
	 * Main screen width constant (px)
	 */
	public static final int SCREEN_WIDTH = 400;
	/**
	 * Main screen height constant (px)
	 */
	public static final int SCREEN_HEIGHT = 200;
	/**
	 * Main screen x location constant (px)
	 */
	public static final int SCREEN_LOC_X = 300;
	/**
	 * Main screen y location constant (px)
	 */
	public static final int SCREEN_LOC_Y = 300;
	/**
	 * Popout screen width constant (px)
	 */
	public static final int POPOUT_WIDTH = 300;
	/**
	 * Popout screen height constant (px)
	 */
	public static final int POPOUT_HEIGHT = 400;
	/**
	 * Popout screen x location constant (px)
	 */
	public static final int POPOUT_LOC_X = 200;
	/**
	 * Popout screen y location constant(px)
	 */
	public static final int POPOUT_LOC_Y = 200;
	
	/**
	 * Enters the Program. Initializes the DataManger and ScreenManager.
	 * Shows the initial screen
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		DataManager.initialize();
		//Initialize the ScreenManager which will drive the main function of the app
		ScreenManager.initialize(new HomeScreen());
	}
	
	/**
	 * Exit the program. Saves DataManager data
	 */
	public static void exit() {
		//TODO:
		DataManager.save();
		System.exit(0);
	}
	
	/**
	 * Loads sample data to DataManager. Does not save unless prompted
	 */
	public static void loadSampleData() {
		DataManager.sessions.add(new TutoringSession("Test Session A"));
		DataManager.sessions.add(new TutoringSession("Test Session B"));
		DataManager.classNames.add(new ClassName("Test Class A"));
		DataManager.classNames.add(new ClassName("Test Class B"));
		DataManager.proficiencies.add(new Proficiency("Test Proficiency A"));
		DataManager.proficiencies.add(new Proficiency("Test Proficiency B"));
		DataManager.skills.add(new Skill("Test Skill A"));
		DataManager.skills.add(new Skill("Test Skill B"));
		
		DataManager.tutors.add(new User("Test Tutor A", UserType.TUTOR));
		DataManager.tutors.add(new User("Test Tutor B", UserType.TUTOR));
		
		//Uses the last added index (the new example tutors)
		DataManager.tutors.get(DataManager.tutors.size() - 1).availability.add(DataManager.sessions.get(DataManager.sessions.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).classNames.add(DataManager.classNames.get(DataManager.classNames.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).proficiencies.add(DataManager.proficiencies.get(DataManager.proficiencies.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).skills.add(DataManager.skills.get(DataManager.skills.size() - 1));
		DataManager.tutors.get(DataManager.tutors.size() - 1).notes.add("Test Note A");
		DataManager.tutors.get(DataManager.tutors.size() - 1).notes.add("Test Note B");
		
		DataManager.tutees.add(new User("Test Tutee A", UserType.TUTEE));
		DataManager.tutees.add(new User("Test Tutee B", UserType.TUTEE));
		
		//Uses the last added index (the new example tutors)
		DataManager.tutees.get(DataManager.tutees.size() - 1).availability.add(DataManager.sessions.get(DataManager.sessions.size() - 1));
		DataManager.tutees.get(DataManager.tutees.size() - 1).classNames.add(DataManager.classNames.get(DataManager.classNames.size() - 1));
		DataManager.tutees.get(DataManager.tutees.size() - 1).proficiencies.add(DataManager.proficiencies.get(DataManager.proficiencies.size() - 1));
		DataManager.tutees.get(DataManager.tutees.size() - 1).skills.add(DataManager.skills.get(DataManager.skills.size() - 1));
		DataManager.tutees.get(DataManager.tutees.size() - 1).notes.add("Test Note A");
		DataManager.tutees.get(DataManager.tutees.size() - 1).notes.add("Test Note B");
	}
	
}
