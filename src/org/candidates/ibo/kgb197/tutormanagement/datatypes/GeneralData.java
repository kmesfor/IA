package org.candidates.ibo.kgb197.tutormanagement.datatypes;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * The purpose of GeneralData is to store the information for a session, class, proficiency, or skill.
 * GeneralData can be used to represent each type, with GeneralDataType being used to differentiate between each.
 * This data type is serializable.
 */
public class GeneralData implements Serializable {
	//Source: https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	
	//Data
	private final String name;
	private final GeneralDataType type;
	private final String uniqueID = UUID.randomUUID().toString();
	
	//Getter methods
	public String getInfo() {
		return name;
	}
	public GeneralDataType getType() {
		return type;
	}
	public String getUUID() {
		return uniqueID;
	}
	
	/**
	 * Safely delete the GeneralData object by removing it from all User objects that hold it
	 */
	public void destroy() {
		if (type == GeneralDataType.CLASS) {
			//Remove from DataManager
			DataManager.classNames.remove(this);
			//Loop through each tutor
			for (User user : DataManager.tutors) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.classNames.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			//Loop through each tutee
			for (User user : DataManager.tutees) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.classNames.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.SESSION) {
			//Remove from DataManager
			DataManager.sessions.remove(this);
			//Loop through each tutor
			for (User user : DataManager.tutors) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.availability.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			//Loop through each tutee
			for (User user : DataManager.tutees) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.availability.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.SKILL) {
			//Remove from DataManager
			DataManager.skills.remove(this);
			//Loop through each tutor
			for (User user : DataManager.tutors) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.skills.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			//Loop through each tutee
			for (User user : DataManager.tutees) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.skills.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.PROFICIENCY) {
			//Remove from DataManager
			DataManager.proficiencies.remove(this);
			//Loop through each tutor
			for (User user : DataManager.tutors) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.proficiencies.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			//Loop through each tutee
			for (User user : DataManager.tutees) {
				//Remove the user's data if the UUID of the data matches the UUID of the user's data
				user.proficiencies.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else {
			//Print an error message
			System.out.println("Invalid GeneralData was attempted to be destroyed!");
		}
	}
	
	//Constructor
	public GeneralData(String name, GeneralDataType type) {
		this.name = name;
		this.type = type;
	}
}
