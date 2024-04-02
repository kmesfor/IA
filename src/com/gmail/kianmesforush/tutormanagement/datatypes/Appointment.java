package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;

public class Appointment implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final User tutor;
	private final User tutee;
	private final GeneralData session;
	private final int duration;
	
	public Appointment(User tutor, User tutee, GeneralData session, int duration) {
		this.tutor = tutor;
		this.tutee = tutee;
		this.session = session;
		this.duration = duration;
	}
	
	public User getTutor() {
		return tutor;
	}
	
	public User getTutee() {
		return  tutee;
	}
	
	public GeneralData getSession() {
		return  session;
	}
	
	public int getDuration() {
		return  duration;
	}
}
