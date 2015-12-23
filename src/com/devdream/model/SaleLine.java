package com.devdream.model;

import com.devdream.util.MathHelper;

/**
 * The SalesLine is the line that forms a whole
 * Sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class SaleLine {

	//
	// Attributes
	private ShopOffer offer;
	private int quantity;

	//
	// Constructor
	public SaleLine(ShopOffer offer, int quantity) {
		setOffer(offer);
		setQuantity(quantity);
	}

	//
	// Getters && Setters
	public ShopOffer getOffer() {
		return offer;
	}
	
	private void setOffer(ShopOffer offer) {
		this.offer = offer;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = (!MathHelper.isNegativeNumber(quantity)) ? quantity : 0;
	}
	
}
