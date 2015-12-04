package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.data.bind.Intent;
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
	public void subscribeClient(final String ID, String name, float cash, boolean isGold) {
		if (isGold)
			Intent.getInstance().setNewClient(new GoldClient(ID, name, new SubscriberCard(cash)));
		else
			Intent.getInstance().setNewClient(new Client(ID, name, new SubscriberCard(cash)));
	}

}
