package com.devdream.model;

public class GoldClient extends Client {

	//
	// Attributes
	private static int DISCOUNT_PERCENTAGE = 30;
	
	//
	// Constructors
	public GoldClient(String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
	}

	//
	// Methods
	
	//
	// Getters and Setters
	public static int getDiscountPercentaje() {
		return DISCOUNT_PERCENTAGE;
	}
	
}