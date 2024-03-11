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
	 * @return An object that should be cast to the type that was initially serialized
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
