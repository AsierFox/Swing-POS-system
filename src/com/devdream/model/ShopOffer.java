package com.devdream.model;

import com.devdream.helper.MathHelper;
import com.devdream.helper.StringHelper;

/**
 * This abstract class Wraps all the product types and
 * services that the shops offers to the clients.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public abstract class ShopOffer {

	//
	// Attributes
	public final int ID;
	private String name, description;
	private float price;
	
	//
	// Constructor
	public ShopOffer(final int ID, String name, String description, float price) {
		this.ID = ID;
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	//
	// Methods
	public boolean isProduct() {
		return this instanceof Product;
	}
	
	public boolean isService() {
		return this instanceof Service;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	//
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	
	public String getFormattedPrice() {
		return StringHelper.formatAmount(getPrice());
	}
	
	public void setPrice(float price) {
		this.price = (!MathHelper.isNegativeNumber((int) price))
				? price
				: 0;
	}

}
