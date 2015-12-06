package com.devdream.model;

import com.devdream.exception.CashNotValidException;
import com.devdream.helper.MathHelper;

/**
 * The subscriber cash is a must of the client for
 * paying in the shop. Without it, the user can not pay.
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
	public void chargeMoney(double cash) throws CashNotValidException {
		if (MathHelper.isNegativeNumber((int) cash)) {
			throw new CashNotValidException();
		}
		this.cash += cash;
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
