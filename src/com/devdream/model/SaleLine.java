package com.devdream.model;

import com.devdream.helper.MathHelper;

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
		this.offer = offer;
		this.quantity = quantity;
	}
	
	//
	// Method
	public void changeProduct(ShopOffer product) {
		this.offer = product;
	}
	
	public void changeQuantity(int quantity) {
		this.quantity = (!MathHelper.isNegativeNumber(quantity)) ? quantity : 0;
	}

	//
	// Getters && Setters
	public ShopOffer getProduct() {
		return offer;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}