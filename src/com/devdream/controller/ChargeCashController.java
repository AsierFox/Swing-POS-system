package com.devdream.controller;

import com.devdream.exception.CashFormatException;
import com.devdream.model.Client;
import com.devdream.util.MathHelper;

/**
 * Class that controls the clients charge transaction to the
 * subscriber card.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ChargeCashController extends Controller {

	private Client client;
	
	public ChargeCashController(Client client) {
		this.client = client;
	}

	//
	// Methods
	/**
	 * Charges cash to a specific client.
	 * @param client Client to charge the cash
	 * @param amount The amount of money to to the client
	 * @throws CashFormatException
	 */
	public void chargeMoney(String amount) throws CashFormatException {
		if (!MathHelper.isNumeric(amount)) {
			throw new CashFormatException();
		}
		client.charge(Double.parseDouble(amount));
	}
	
	/** Gets the client to charge cash. */
	public Client getClient() {
		return client;
	}
	
}
