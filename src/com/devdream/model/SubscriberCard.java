package com.devdream.model;

import com.devdream.helper.MathHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class SubscriberCard {

	//
	// Attributes
	private double cash;
	
	//
	// Constructor
	public SubscriberCard(float cash) {
		this.cash = cash;
	}

	//
	// Methods
	public void chargeMoney(double cash) {
		if (!MathHelper.isNegativeNumber((int) cash)) {
			this.cash += cash;
		}
	}
	
	public boolean canAffordPayment(double amount) {
		return amount <= cash;
	}
	
	//
	// Getters and Setters
	public double getCash() {
		return cash;
	}
	
}