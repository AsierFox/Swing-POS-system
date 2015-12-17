package com.devdream.model;

/**
 * Special product for shop offer.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Service extends ShopOffer {

	//
	// Constructors
	public Service(final int ID, String name, String description, float price) {
		super(ID, name, description, price);
	}
	
	//
	// toString
	@Override
	public String toString() {
		return getName();
	}

}
