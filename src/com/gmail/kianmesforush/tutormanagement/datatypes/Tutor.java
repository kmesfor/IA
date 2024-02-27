package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.util.ArrayList;

public class Tutor extends User {
	public final ArrayList<Class> priorClasses = new ArrayList<>();
	public final ArrayList<Skill> skills = new ArrayList<>();
	public final ArrayList<Proficiency> proficiencies = new ArrayList<>();
	
	public Tutor(String name) {
		super(name);
	}
}