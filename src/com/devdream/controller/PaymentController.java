package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.exception.CantAffordException;
import com.devdream.exception.EmptyPaymentException;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Sale;

/**
 * This controller manages the payment of a sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class PaymentController extends Controller {

	public PaymentController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
	}

	public void processPayment(Sale sale, Client client, Commercial commercial) throws EmptyPaymentException, CantAffordException {
		if (sale.getSaleLines().isEmpty()) {
			throw new EmptyPaymentException();
		}
		if (client.canAffordPayment(sale.getTotal())) {
			throw new CantAffordException();
		}
		
		super.changeView();
	}
	
}
