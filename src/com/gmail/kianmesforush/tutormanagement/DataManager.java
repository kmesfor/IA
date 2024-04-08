package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.*;
import com.gmail.kianmesforush.tutormanagement.screens.ErrorScreen;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
	public static ArrayList<GeneralData> sessions = new ArrayList<>();
	public static ArrayList<GeneralData> classNames = new ArrayList<>();
	public static ArrayList<GeneralData> proficiencies = new ArrayList<>();
	public static ArrayList<GeneralData> skills = new ArrayList<>();
	public static ArrayList<User> tutors = new ArrayList<>();
	public static ArrayList<User> tutees = new ArrayList<>();
	public static ArrayList<Appointment> appointments = new ArrayList<>();
	
	/**
	 * Initialize the DataManager by deserializing field data located in the data/ directory
	 */
	//Suppress an unchecked cast warning, DataManager is the only class that will touch the data files,
	//thus the cast is safe as long as serialization and deserialization use the same type
	@SuppressWarnings("unchecked")
	public static void initialize() {
		sessions = (ArrayList<GeneralData>) deserialize("data/sessions");
		classNames = (ArrayList<GeneralData>) deserialize("data/classes");
		proficiencies = (ArrayList<GeneralData>) deserialize("data/proficiencies");
		skills = (ArrayList<GeneralData>) deserialize("data/skills");
		tutors = (ArrayList<User>) deserialize("data/tutors");
		tutees = (ArrayList<User>) deserialize("data/tutees");
		appointments = (ArrayList<Appointment>) deserialize("data/appointments");
	}
	
	/**
	 * Save field data to external files located in the data/ directory
	 */
	public static void save() {
		serialize(sessions, "data/sessions");
		serialize(classNames, "data/classes");
		serialize(proficiencies, "data/proficiencies");
		serialize(skills, "data/skills");
		serialize(tutors, "data/tutors");
		serialize(tutees, "data/tutees");
		serialize(appointments, "data/appointments");
	}
	
	/**
	 * Check if a list of GeneralData elements contains the same UUID as a provided GeneralData element.
	 * <br><br>The usage of UUIDs and comparing UUIDs for GeneralData is necessary to ensure that data is persistent.
	 * 	Without UUIDs, when serializing and deserializing data, a new GeneralData instance is created for each
	 * 	GeneralData (correct behavior) and for each GeneralData stored within a User (incorrect behavior). This
	 * 	leads to unnecessary duplicate GeneralData being created and the program unable to match up GeneralData from
	 * 	DataManager.(GeneralDataType) and individual data from Users.
	 * 	<br><br>Example without UUIDs:
	 * 	The following classes would be created, but would not be connected:
	 * 	DataManager.classes -> new ClassName("English")
	 * 	DataManager.tutors[0] -> new ClassName("English")
	 * 	<br><br>The two english classes, although they should be connected (not through name though), are not.
	 * 	<br><br>Example with UUIDs:
	 * 	The following classes would be created, and could be linked by UUID:
	 * 	DataManager.classes -> new ClassName("English") UUID: 12345
	 * 	DataManager.tutors[0] -> new ClassName("English) UUID: 12345
	 * <br><br>Although new classes are still created (due to the method of serialization), these classes are linkable by UUIDs
	 * 	and can be considered equivalent.
	 *
	 * @param list the list of elements to check for a matching UUID
	 * @param data the provided element
	 * @return true if any element in the list has the same UUID as the provided element
	 */
	public static Boolean listContains(ArrayList<GeneralData> list, GeneralData data) {
		for (GeneralData listElement : list) {
			if (listElement.getUUID().equals(data.getUUID())) return true;
		}
		return false;
	}
	
	/**
	 * Same usage as {@link DataManager#listContains(ArrayList, GeneralData)}, instead for User UUIDs
	 *
	 * @param list the list of elements to check for a matching UUID
	 * @param user the provided user element
	 * @return true if any element in the list has the same UUID as the provided element
	 */
	public static Boolean listContains(ArrayList<User> list, User user) {
		for (User listElement : list) {
			if (listElement.getUUID().equals(user.getUUID())) return true;
		}
		return false;
	}
	
	/**
	 * Serialize an object and save to an external file.
	 * @param object the object to be serialized
	 * @param path filepath to save the object
	 */
	private static void serialize(Object object, String path) {
		try {
			//Create a new output stream to write to files
			FileOutputStream fileStream = new FileOutputStream(path);
			//Create an output stream specifically for writing objects
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			//Write the object
			objectStream.writeObject(object);
			//Close the file streams
			objectStream.close();
			fileStream.close();
		} catch (Exception err) {
			//Display an error screen that prevents the user from interacting until
			// the error is resolved and the program is restarted
			ScreenManager.setCurrentScreen(new ErrorScreen("Error serializing data: " + err.getMessage()));
			// Also print message as the error may have occurred during an exit-save
			System.out.println(err.getMessage());
		}
	}
	
	/**
	 * Deserialize an object by reading serialized data from an external file.
	 * @param path the file path of the file to deserialize
	 * @return an object that was initially serialized, must cast the type before usage
	 */
	// https://www.baeldung.com/java-how-to-create-a-file
	private static Object deserialize(String path) {
		Object returnValue;
		try {
			FileInputStream fileStream = new FileInputStream(path);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			// read the file
			returnValue = objectStream.readObject();
			//close streams
			fileStream.close();
			objectStream.close();
		} catch (FileNotFoundException err) {
			//Try to create the file if it is not found
			File file = new File(path);
			try {
				//Create file, show error if the file could not be created
				if (!file.createNewFile()) ScreenManager.setCurrentScreen(new ErrorScreen("Error creating data file: " + err.getMessage()));
			} catch (IOException fileError) {
				//Show any other type of error
				ScreenManager.setCurrentScreen(new ErrorScreen("Error creating data file: " + err.getMessage()));
			}
			//return an empty value because the file was just created
			return new ArrayList<>();
			
		} catch (IOException | ClassNotFoundException err) {
			//file is empty or other error
			return new ArrayList<>();
		}
		//return the read value
		return returnValue;
	}
}
