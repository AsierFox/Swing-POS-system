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
public class SalesLine {

	//
	// Attributes
	private ShopOffer product;
	private int quantity;

	//
	// Constructor
	public SalesLine(ShopOffer product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	//
	// Method
	public void changeProduct(ShopOffer product) {
		this.product = product;
	}
	
	public void changeQuantity(int quantity) {
		this.quantity = (!MathHelper.isNegativeNumber(quantity)) ? quantity : 0;
	}

	//
	// Getters && Setters
	public ShopOffer getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}