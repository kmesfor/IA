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
	public final ArrayList<TutoringSession> availability = new ArrayList<>();
	public final ArrayList<ClassName> classNames = new ArrayList<>();
	public final ArrayList<Skill> skills = new ArrayList<>();
	public final ArrayList<Proficiency> proficiencies = new ArrayList<>();
	public final ArrayList<String> notes = new ArrayList<>();
	
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