package com.devdream.model;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class GoldClient extends Client {

	//
	// Attributes
	private static float DISCOUNT_PERCENTAGE = 30;
	
	//
	// Constructors
	public GoldClient(String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
	}
	
	//
	// Getters and Setters
	public static float getDiscountPercentaje() {
		return DISCOUNT_PERCENTAGE;
	}
	
}