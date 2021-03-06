package com.devdream.model;

import com.devdream.util.StringHelper;

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
	/**
	 * Pay the bill.
	 * @param amount The amount to pay
	 */
	public void pay(double amount) {
		subscriberCard.retireMoney(amount);
		subscriberCard.addSpentCash(amount);
	}

	/**
	 * Charge cash on the subscriber client card.
	 * @param amount The amount to pay
	 */
	public void charge(double amount) {
		subscriberCard.chargeCash(amount);
	}
	
	/**
	 * Returns true if the client can afford payment.
	 * @param total The total that the client needs to afford to pay the bill
	 * @return
	 */
	public boolean canAffordPayment(double total) {
		return getCash() >= total;
	}
	
	/** Returns if the client is a gold client */
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
	
	public String getFormattedCash() {
		return StringHelper.formatAmount(getCash()) + Shop.COIN_BADGE;
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
