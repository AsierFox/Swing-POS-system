package com.devdream.model;

import com.devdream.helper.MathHelper;
import com.devdream.helper.StringHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Product {

	//
	// Attributes
	private final int ID;
	private String name, description;
	private float price;
	//
	// Constructor
	public Product(final int ID, String name, String description, float price) {
		this.ID = ID;
		setName(name);
		setDescription(description);
		setPrice(price);
	}

	//
	// Methods
	
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