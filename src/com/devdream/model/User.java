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

	//
	// Constructor
	public User(final String ID, String name) {
		this.ID = ID;
		setName(name);
	}

	// toString
	@Override
	public String toString() {
		return getName();
	}
	
	//
	// Getters && Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
