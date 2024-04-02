package com.gmail.kianmesforush.tutormanagement.datatypes;

import com.gmail.kianmesforush.tutormanagement.DataManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class GeneralData implements Serializable {
	//https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final GeneralDataType type;
	private final String uniqueID = UUID.randomUUID().toString();
	
	public String getInfo() {
		return name;
	}
	public GeneralDataType getType() {
		return type;
	}
	public String getUUID() {
		return uniqueID;
	}
	
	public void destroy() {
		if (type == GeneralDataType.CLASS) {
			DataManager.classNames.remove(this);
			for (User user : DataManager.tutors) {
				user.classNames.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			for (User user : DataManager.tutees) {
				user.classNames.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.SESSION) {
			DataManager.sessions.remove(this);
			for (User user : DataManager.tutors) {
				user.availability.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			for (User user : DataManager.tutees) {
				user.availability.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.SKILL) {
			DataManager.skills.remove(this);
			for (User user : DataManager.tutors) {
				user.skills.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			for (User user : DataManager.tutees) {
				user.skills.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else if (type == GeneralDataType.PROFICIENCY) {
			DataManager.proficiencies.remove(this);
			for (User user : DataManager.tutors) {
				user.proficiencies.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
			for (User user : DataManager.tutees) {
				user.proficiencies.removeIf(data -> data.getUUID().equals(this.getUUID()));
			}
		} else {
			System.out.println("Invalid GeneralData was attempted to be destroyed!");
		}
	}
	
	public GeneralData(String name, GeneralDataType type) {
		this.name = name;
		this.type = type;
	}
}
