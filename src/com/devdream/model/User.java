package com.devdream.model;

import com.devdream.helper.ErrorHandler;

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
	private SubscriberCard subscriberCard; // TODO suscriber or subscriber
	
	public User(final String ID, String name, SubscriberCard subscriberCard) {
		this.ID = ID;
		setName(name);
		this.subscriberCard = subscriberCard;
	}

	public void setName(String name) {
		this.name = (!ErrorHandler.isStringNull(name)) ?
				name :
				"null";
	}

}