package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	//https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	private final String name;
	private final UserType type;
	public final ArrayList<GeneralData> availability = new ArrayList<>();
	public final ArrayList<GeneralData> classNames = new ArrayList<>();
	public final ArrayList<GeneralData> skills = new ArrayList<>();
	public final ArrayList<GeneralData> proficiencies = new ArrayList<>();
	public final ArrayList<String> notes = new ArrayList<>();
	
	/**
	 * A instantiation method intended to create a deep clone of a User.
	 * @param user the user to clone
	 */
	public User(User user) {
		this.name = user.getName();
		this.type = user.getType();
		availability.addAll(user.availability);
		classNames.addAll(user.classNames);
		skills.addAll(user.skills);
		proficiencies.addAll(user.proficiencies);
		notes.addAll(user.notes);
	}
	public User(String name, UserType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UserType getType() {
		return type;
	}
}