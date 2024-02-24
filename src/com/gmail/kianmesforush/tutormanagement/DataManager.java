package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.Tutor;
import com.gmail.kianmesforush.tutormanagement.datatypes.TutoringSession;
import com.gmail.kianmesforush.tutormanagement.screens.ErrorScreen;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

//Serialization method via https://www.tutorialspoint.com/java/java_serialization.htm
public class DataManager {
	public static ArrayList<TutoringSession> sessions = new ArrayList<>();
	public static ArrayList<Tutor> tutors = new ArrayList<>();
	
	//Compiler causing unnecessary errors, DataManager is the only class that will touch the data files
	@SuppressWarnings("unchecked")
	public static void initialize() {
		sessions = (ArrayList<TutoringSession>) deserialize("data/sessions");
		tutors = (ArrayList<Tutor>) deserialize("data/tutors");
	}
	
	public static void save() {
		serialize(sessions, "data/sessions");
		serialize(tutors, "data/tutors");
	}


	private static void serialize(Object object, String path) {
		try {
			FileOutputStream fileStream = new FileOutputStream(path);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(object);
			objectStream.close();
			fileStream.close();
		} catch (Exception err) {
			// Also print message as it may be present during an exit-save
			System.out.println(err.getMessage());
			ScreenManager.setCurrentScreen(new ErrorScreen("Error serializing data: " + err.getMessage()));
		}
	}
	
	// https://www.baeldung.com/java-how-to-create-a-file
	private static Object deserialize(String path) {
		Object returnValue;
		try {
			//Initiate file and object streams
			File file = new File(path);
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
