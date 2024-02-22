package com.gmail.kianmesforush.tutormanagement.data;

import java.util.ArrayList;

public abstract class User {
	private String name;
	private final ArrayList<TutoringSession> availability = new ArrayList<>();
	private final ArrayList<String> notes = new ArrayList<>();
	
	public String getName() { return this.name; }
	
	public ArrayList<TutoringSession> getAvailability() { return this.availability; }
	
	public void setAvailability(TutoringSession session, Boolean isAvailable) {
		if (isAvailable) {
			if (!availability.contains(session)) availability.add(session);
		} else {
			availability.remove(session);
		}
	}
	
	public ArrayList<String> getNotes() { return this.notes; }
	
	public void addNote(String note) { notes.add(note); }
	
	public void removeNote(int index) { notes.remove(index); }
}
