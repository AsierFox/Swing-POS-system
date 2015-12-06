package com.devdream.model;

import com.devdream.helper.DateHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Client extends User {

	//
	// Attributes
	private SubscriberCard subscriberCard;
	private String joinedDate; // TODO Joined date to contructor

	//
	// Constructors
	public Client(final String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name);
		this.subscriberCard = subscriberCard;
	}
	
	//
	// Methods
	public boolean canAffordPayment(double total) {
		return subscriberCard.getCash() <= total;
	}
	
	public boolean isGoldClient() {
		return this instanceof GoldClient;
	}

	//
	// Getters and Setters
	public SubscriberCard getSubscriberCard() {
		return subscriberCard;
	}
	
	public String getFechaAlta() {
		return joinedDate;
	}

	public void setFechaAlta(int day, int month, int year) {
		this.joinedDate = DateHelper.getCustomDate(day, month, year);
	}
	
}
