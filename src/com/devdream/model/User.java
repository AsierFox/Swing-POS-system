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
	private SubscriberCard subscriberCard;

	//
	// Constructor
	public User(final String ID, String name, SubscriberCard subscriberCard) {
		this.ID = ID;
		setName(name);
		this.subscriberCard = subscriberCard;
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