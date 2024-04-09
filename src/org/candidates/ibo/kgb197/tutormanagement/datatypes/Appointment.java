package org.candidates.ibo.kgb197.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;

/**
 * The purpose of the Appointment data class is to store the data a created appointment.
 * This class stores the appointment’s tutor, tutee, session, and duration.
 * This data type is serializable.
 */
public class Appointment implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	//Data
	private final User tutor;
	private final User tutee;
	private final GeneralData session;
	private final int duration;
	
	//Initialization
	public Appointment(User tutor, User tutee, GeneralData session, int duration) {
		this.tutor = tutor;
		this.tutee = tutee;
		this.session = session;
		this.duration = duration;
	}
	
	//Getter methods
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
