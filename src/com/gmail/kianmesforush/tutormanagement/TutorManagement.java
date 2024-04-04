package com.gmail.kianmesforush.tutormanagement;
//TODO: rename the package to not have any associating characteristics?
import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.screens.HomeScreen;

/**
 * The main class of the program.
 */
public class TutorManagement {
	/**
	 * Main screen width constant (px)
	 */
	public static final int SCREEN_WIDTH = 700;
	/**
	 * Main screen height constant (px)
	 */
	public static final int SCREEN_HEIGHT = 400;
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
		User john = new User("John", UserType.TUTOR);
		User bob = new User("Bob", UserType.TUTOR);
		User paul = new User("Paul", UserType.TUTOR);
		
		User sarah = new User("Sarah", UserType.TUTEE);
		User alice = new User("Alice", UserType.TUTEE);
		User holly = new User("Holly", UserType.TUTEE);
		
		GeneralData session1 = new GeneralData("Jan 1 @ 5pm", GeneralDataType.SESSION);
		GeneralData session2 = new GeneralData("Jan 3 @ 7pm", GeneralDataType.SESSION);
		
		GeneralData class1 = new GeneralData("English", GeneralDataType.CLASS);
		GeneralData class2 = new GeneralData("Math", GeneralDataType.CLASS);
		
		GeneralData prof1 = new GeneralData("Data Analysis", GeneralDataType.PROFICIENCY);
		GeneralData prof2 = new GeneralData("Data Entry", GeneralDataType.PROFICIENCY);
		
		GeneralData skill1 = new GeneralData("Excel", GeneralDataType.SKILL);
		GeneralData skill2 = new GeneralData("Word", GeneralDataType.SKILL);
		
		DataManager.sessions.add(session1);
		DataManager.sessions.add(session2);
		DataManager.classNames.add(class1);
		DataManager.classNames.add(class2);
		DataManager.proficiencies.add(prof1);
		DataManager.proficiencies.add(prof2);
		DataManager.skills.add(skill1);
		DataManager.skills.add(skill2);
		
		john.availability.add(session1);
		john.availability.add(session2);
		john.classNames.add(class1);
		john.classNames.add(class2);
		john.proficiencies.add(prof1);
		john.proficiencies.add(prof2);
		john.skills.add(skill1);
		john.skills.add(skill2);
		john.notes.add("Works well in groups");
		
		bob.availability.add(session1);
		bob.classNames.add(class1);
		bob.proficiencies.add(prof1);
		bob.skills.add(skill1);
		
		DataManager.tutors.add(john);
		DataManager.tutors.add(bob);
		DataManager.tutors.add(paul);
		
		sarah.availability.add(session1);
		sarah.availability.add(session2);
		sarah.classNames.add(class1);
		sarah.classNames.add(class2);
		sarah.proficiencies.add(prof1);
		sarah.proficiencies.add(prof2);
		sarah.skills.add(skill1);
		sarah.skills.add(skill2);
		sarah.notes.add("Works well in groups");
		
		alice.availability.add(session1);
		alice.classNames.add(class1);
		alice.proficiencies.add(prof1);
		alice.skills.add(skill1);
		
		DataManager.tutees.add(sarah);
		DataManager.tutees.add(alice);
		DataManager.tutees.add(holly);
	}
	
}
