package com.devdream.model;

import com.devdream.helper.StringHelper;

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
	private SubscriberCard subscriberCard;
	
	public User(final String ID, String name, SubscriberCard subscriberCard) {
		this.ID = ID;
		setName(name);
		this.subscriberCard = subscriberCard;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = (!StringHelper.isStringNull(name)) ?
				name :
				"null"; // TODO What to do here
	}

}