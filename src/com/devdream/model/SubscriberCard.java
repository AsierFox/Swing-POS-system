package com.devdream.model;

import com.devdream.util.MathHelper;

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
	private double spentCash;
	
	//
	// Constructor
	public SubscriberCard(float cash) {
		setCash(cash);
	}
	
	//
	// Methods
	/**
	 * Charge cash to the card.
	 * @param amount The amount to charge
	 */
	protected void chargeCash(double amount) {
		cash += amount;
	}
	 
	/**
	 * Retires cash to the card.
	 * @param amount The amount to retire
	 */
	protected void retireMoney(double amount) {
		cash -= amount;
	}
	 
	/**
	 * Checks if the client subscriber card has enough cash to afford the payment.
	 * @param amount The amount to check
	 */
	protected boolean canAffordPayment(double amount) {
		return amount <= cash;
	}
	
	/**
	 * Increment the spent cash in the shop.
	 * @param cash The cash that the client spent on the sale
	 */
	protected void addSpentCash(double cash) {
		spentCash += cash;
	}
	
	//
	// Getters and Setters
	protected double getCash() {
		return cash;
	}
	
	private void setCash(double cash) {
		this.cash = MathHelper.isNegativeNumber((int) cash) ? 0 : cash;
	}
	
	protected double getSpentCash() {
		return spentCash;
	}

}
