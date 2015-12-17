package com.devdream.model;

/**
 * Special product of shop offer.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Product extends ShopOffer {

	//
	// Constructors
	public Product(final int ID, String name, String description, float price) {
		super(ID, name, description, price);
	}
	
	//
	// toString
	@Override
	public String toString() {
		return getName();
	}
	
}
