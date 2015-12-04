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
	private final int ID;
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
	
	//
	// Getters and Setters
	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = (!StringHelper.isStringNull(name)) ?
				name :
				"null";// TODO Dont know how to set if parametter null
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = (!StringHelper.isStringNull(description)) ?
				description :
				"null";// TODO Dont know how to set if parametter null
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = (!MathHelper.isNegativeNumber((int) price)) ?
				price :
				0;
	}
}