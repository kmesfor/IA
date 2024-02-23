package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;

public abstract class GeneralDataType implements Serializable {
	//https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final String name;
	public String getInfo() {
		return name;
	}
	
	GeneralDataType(String name) {
		this.name = name;
	}
}
