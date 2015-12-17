package com.devdream.controller;

import com.devdream.exception.CashFormatException;
import com.devdream.helper.MathHelper;
import com.devdream.model.Client;

public class ChargeCashController extends Controller {

	public void chargeMoney(Client client, String amount) throws CashFormatException {
		if (!MathHelper.isNumeric(amount)) {
			throw new CashFormatException();
		}
		client.charge(Double.parseDouble(amount));
	}
	
}
