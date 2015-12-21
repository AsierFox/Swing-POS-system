package com.devdream.model;

/**
 * This class wraps all users types
 * of the system.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public abstract class User {

	//
	// Attributes
	public final String ID;
	private String name;
	private String surname;

	//
	// Constructor
	public User(final String ID, String name, String surname) {
		this.ID = ID;
		setName(name);
		setSurname(surname);
	}

	// toString
	@Override
	public String toString() {
		return getName() + " " + getSurname();
	}
	
	//
	// Getters && Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
