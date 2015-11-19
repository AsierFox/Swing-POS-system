package com.devdream.model;

/**
 * This class wraps all users of the system
 * @author Asier Gonzalez
 */
public class User {

	public final String NIF; // TODO public constant ?
							// TODO NIF en ingles
	private String name;
	
	public User(final String NIF) {
		this.NIF = NIF;
	}

}