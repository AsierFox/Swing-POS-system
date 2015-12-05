package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.data.bind.Intent;
import com.devdream.exception.CashNotValidException;
import com.devdream.exception.TextNotValidException;
import com.devdream.helper.MathHelper;
import com.devdream.helper.StringHelper;
import com.devdream.model.Client;
import com.devdream.model.GoldClient;
import com.devdream.model.SubscriberCard;

/**
 * Class that is going to manage client subscriptions.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class SubscribeClientController extends Controller {

	//
	// Constructors
	public SubscribeClientController() {
		super();
	}
	
	public SubscribeClientController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
	}

	//
	// Methods
	public void subscribeClient(final String ID, String name, String cashTxt, boolean isGold) throws CashNotValidException, TextNotValidException {
		if (StringHelper.isStringNull(ID)) {
			throw new TextNotValidException("ID");
		}
		if (StringHelper.isStringNull(name) || MathHelper.isNumeric(name)) {
			throw new TextNotValidException("Name");
		}
		if (StringHelper.isStringNull(cashTxt)) {
			throw new TextNotValidException("Cash");
		}
		
		float cash = .0f;
		
		try {
			cash = Float.parseFloat(cashTxt);
		} catch(NumberFormatException err) {
			throw new CashNotValidException();
		}
		
		if (isGold)
			Intent.getInstance().setNewClient(new GoldClient(ID.trim(), name.trim(), new SubscriberCard(cash)));
		else
			Intent.getInstance().setNewClient(new Client(ID.trim(), name.trim(), new SubscriberCard(cash)));
	}

}
