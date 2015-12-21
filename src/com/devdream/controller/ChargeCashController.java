package com.devdream.controller;

import com.devdream.exception.CashFormatException;
import com.devdream.helper.MathHelper;
import com.devdream.model.Client;

/**
 * Class that controls the clients charge transaction to the
 * subscriber card.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ChargeCashController extends Controller {

	//
	// Methods
	public void chargeMoney(Client client, String amount) throws CashFormatException {
		if (!MathHelper.isNumeric(amount)) {
			throw new CashFormatException();
		}
		client.charge(Double.parseDouble(amount));
	}
	
}
