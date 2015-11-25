package com.devdream.model;

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
	// Methods
	
	//
	// Getters and Setters
	public static float getDiscountPercentaje() {
		return DISCOUNT_PERCENTAGE;
	}
	
}