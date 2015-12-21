package com.devdream.model;

/**
 * The client of the shop.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Client extends User {

	//
	// Attributes
	private SubscriberCard subscriberCard;

	//
	// Constructors
	public Client(final String ID, String name, String surname, SubscriberCard subscriberCard) {
		super(ID, name, surname);
		setSubscriberCard(subscriberCard);
	}
	
	//
	// Methods
	public void pay(double amount) {
		subscriberCard.retireMoney(amount);
		subscriberCard.addSpentCash(amount);
	}

	public void charge(double amount) {
		subscriberCard.chargeCash(amount);
	}
	
	public boolean canAffordPayment(double total) {
		return subscriberCard.getCash() >= total;
	}
	
	public boolean isGoldClient() {
		return this instanceof GoldClient;
	}
	
	//
	// Getters and Setters
	public SubscriberCard getSubscriberCard() {
		return subscriberCard;
	}
	
	public double getCash() {
		return subscriberCard.getCash();
	}
	
	public double getSpentCash() {
		return subscriberCard.getSpentCash();
	}
	
	public double getRemainingCashGoldClient() {
		return ((GoldClient.AMOUNT_FOR_GOLD_CLIENT - getSpentCash()) > 0)
				? GoldClient.AMOUNT_FOR_GOLD_CLIENT - getSpentCash()
				: 0;
	}
	
	private void setSubscriberCard(SubscriberCard subscriberCard) {
		this.subscriberCard = subscriberCard;
	}
	
}
