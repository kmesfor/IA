package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.util.ArrayList;

public class Tutee extends User {
	public final ArrayList<Class> classesNeeded = new ArrayList<>();
	public final ArrayList<Skill> skillsNeeded = new ArrayList<>();
	public final ArrayList<Proficiency> proficienciesNeeded = new ArrayList<>();
	
	public Tutee(String name) {
		super(name);
	}
}
