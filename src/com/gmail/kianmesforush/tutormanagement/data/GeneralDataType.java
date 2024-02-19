package com.gmail.kianmesforush.tutormanagement.data;

public abstract class GeneralDataType {
	String name;
	String getInfo() {
		return name;
	}
	
	GeneralDataType(String name) {
		this.name = name;
	}
}
