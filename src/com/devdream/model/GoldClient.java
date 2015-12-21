package com.devdream.model;

import com.devdream.data.bind.Intent;

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
	public static final int AMOUNT_FOR_GOLD_CLIENT = 4; // TODO Change the amount
	public static float DISCOUNT_PERCENTAGE = 30;
	
	//
	// Constructors
	public GoldClient(final String ID, String name, String surname, SubscriberCard subscriberCard) {
		super(ID, name, surname, subscriberCard);
	}
	
	//
	// Methods
	public static void convertToGoldClient(Client client) {
		Intent.getInstance().getClients().put(client.ID,
			new GoldClient(client.ID, client.getName(), client.getSurname(), client.getSubscriberCard()));
	}
	
}
