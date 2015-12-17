package com.devdream.model;

/**
 * The gold client is a special client that achieves
 * an amount of cash spent in the shop and getting discounts.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class GoldClient extends Client {

	//
	// Attributes
	public static final int AMOUNT_FOR_GOLD_CLIENT = 4;
	public static float DISCOUNT_PERCENTAGE = 30;
	
	//
	// Constructors
	public GoldClient(String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
	}
	
	//
	// Methods
	public static void convertToGoldClient(Client client) {
//		try {
			client = new GoldClient("213", "Asier", new SubscriberCard(300));
			client.setName("asd");
			// TODO LOL
//		} catch (MethodN e) {
//			e.printStackTrace();
//		}
	}
	
	//
	// Getters and Setters
	public static float getDiscountPercentaje() {
		return DISCOUNT_PERCENTAGE;
	}
	
}
