package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;

public class GeneralData implements Serializable {
	//https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final GeneralDataType type;
	
	public String getInfo() {
		return name;
	}
	public GeneralDataType getType() {
		return type;
	}
	
	public GeneralData(String name, GeneralDataType type) {
		this.name = name;
		this.type = type;
	}
}
