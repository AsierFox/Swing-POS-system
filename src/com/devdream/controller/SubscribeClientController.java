package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.data.bind.Intent;
import com.devdream.exception.CashFormatException;
import com.devdream.exception.ClientAlreadyExists;
import com.devdream.exception.TextNotValidException;
import com.devdream.helper.MathHelper;
import com.devdream.helper.StringHelper;
import com.devdream.model.Client;
import com.devdream.model.GoldClient;
import com.devdream.model.SubscriberCard;

/**
 * Class that controls the subscribe action when a new client
 * is registered to the shop.
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
	/**
	 * Subscribes a client
	 * @param The information of the client needs to register on the shop.
	 * @throws CashFormatException
	 * @throws TextNotValidException
	 * @throws ClientAlreadyExists
	 */
	public void subscribeClient(final String ID, String name, String surname, String cashTxt, boolean isGold)
			throws CashFormatException, TextNotValidException, ClientAlreadyExists
	{
		if (StringHelper.isStringNull(ID)) {
			throw new TextNotValidException("ID");
		}
		if (Intent.getInstance().getClients().containsKey(ID)) {
			throw new ClientAlreadyExists();
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
			throw new CashFormatException();
		}
		if (isGold) {
			Intent.getInstance().setNewClient(new GoldClient(ID, name, surname, new SubscriberCard(cash)));
		} else {
			Intent.getInstance().setNewClient(new Client(ID, name, surname, new SubscriberCard(cash)));
		}
	}
	
}
