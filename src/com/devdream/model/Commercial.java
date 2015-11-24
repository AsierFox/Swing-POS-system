package com.devdream.model;

import java.util.HashMap;

public class Commercial extends User {

	public Commercial(final String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
		// TODO Auto-generated constructor stub
	}

	private HashMap<Product, Integer> saledProducts;


}