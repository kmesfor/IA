package org.candidates.ibo.kgb197.tutormanagement.datatypes;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Stores the data of a tutor or tutee. User objects can be used for
 * both tutors and tutees by specifying a UserType. A user object holds
 * ArrayLists of each type of GeneralData (sessions, classes, skills, and proficiencies)
 * and a unique user identifier (UUID) to ensure similar User objects are unique.
 * This data type is serializable.
 */
public class User implements Serializable {
	//Source: https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	
	//Data
	private final String name;
	private final UserType type;
	private int hoursCompleted = 0;
	//Generate a new UUID
	private String uniqueID = UUID.randomUUID().toString();
	
	//List of GeneralData objects
	public final ArrayList<GeneralData> availability = new ArrayList<>();
	public final ArrayList<GeneralData> classNames = new ArrayList<>();
	public final ArrayList<GeneralData> skills = new ArrayList<>();
	public final ArrayList<GeneralData> proficiencies = new ArrayList<>();
	public final ArrayList<String> notes = new ArrayList<>();
	
	/**
	 * An instantiation method intended to create a deep clone of a User.
	 * @param user the user to clone
	 */
	public User(User user) {
		this.name = user.getName();
		this.type = user.getType();
		this.uniqueID = user.getUUID();
		this.hoursCompleted = user.getHoursCompleted();
		availability.addAll(user.availability);
		classNames.addAll(user.classNames);
		skills.addAll(user.skills);
		proficiencies.addAll(user.proficiencies);
		notes.addAll(user.notes);
	}
	
	/**
	 * Instantiate a User object
	 * @param name the name of the user
	 * @param type the type of user
	 */
	public User(String name, UserType type) {
		this.name = name;
		this.type = type;
	}
	
	//Getter methods
	public String getName() {
		return this.name;
	}
	
	public UserType getType() {
		return type;
	}
	
	public int getHoursCompleted() {
		return hoursCompleted;
	}
	
	public String getUUID() {
		return uniqueID;
	}
	
	//Add tutoring hours to a User
	public void addHours(int hours) {
		this.hoursCompleted += hours;
	}
	
	/**
	 * A method to check if a user is included in a set of filters.
	 * @param filters A list of GeneralDataType(s) that a user must have to pass.
	 * @return A boolean value representing if a user passes a set of filters
	 * Returns True if the user applies. Returns false is a user does not.
	 */
	public Boolean isIncluded(ArrayList<GeneralData> filters) {
		//Loop through each filter
		for (GeneralData filter : filters) {
			// if the filter type is a class and the user's classes do not include the filter, return false
			if (filter.getType() == GeneralDataType.CLASS && (!DataManager.listContains(classNames, filter))) return false;
			// if the filter type is a session and  the user's sessions do not include the filter, return false
			if (filter.getType() == GeneralDataType.SESSION && (!DataManager.listContains(availability, filter))) return false;
			// if the filter type is a skill and  the user's skills do not include the filter, return false
			if (filter.getType() == GeneralDataType.SKILL && (!DataManager.listContains(skills, filter))) return false;
			// if the filter type is a proficiency and  the user's proficiencies do not include the filter, return false
			if (filter.getType() == GeneralDataType.PROFICIENCY && (!DataManager.listContains(proficiencies, filter))) return false;
		}
		// if all filters are included, return true
		return true;
	}
	
	/**
	 * @return a String representation of the User object
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n-----\n")
				.append("Object: ")
				.append(this.hashCode())
				.append("\n")
				.append("Name: ")
				.append(getName())
				.append("\n")
				.append("Type: ")
				.append(getType())
				.append("\n")
				.append("Classes:");
		classNames.forEach(className -> builder.append(className.getInfo()).append(", "));
		builder.append("\nSessions:");
		availability.forEach(session -> builder.append(session.getInfo()).append(", "));
		builder.append("\nSkills:");
		skills.forEach(skill -> builder.append(skill.getInfo()).append(", "));
		builder.append("\nProficiencies:");
		proficiencies.forEach(proficiency -> builder.append(proficiency.getInfo()).append(", "));
		
		return builder.toString();
	}
}