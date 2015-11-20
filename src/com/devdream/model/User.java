package com.devdream.model;

/**
 * This class wraps all users of the system
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public abstract class User {

	public final String ID;
	private String name;
	private double cash;
	
	public User(final String NIF) {
		this.ID = NIF;
	}

}