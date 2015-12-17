package com.devdream.model;

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
		this.cash = cash;
	}

	//
	// Methods
	protected void chargeCash(double amount) {
		cash += amount;
	}
	
	protected void retireMoney(double amount) {
		cash -= amount;
	}
	
	protected boolean canAffordPayment(double amount) {
		return amount <= cash;
	}
	
	protected void addSpentCash(double cash) {
		spentCash += cash;
	}
	
	//
	// Getters and Setters
	protected double getCash() {
		return cash;
	}
	
	protected double getSpentCash() {
		return spentCash;
	}

}
