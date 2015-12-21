package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.data.bind.Intent;
import com.devdream.exception.CantAffordException;
import com.devdream.exception.EmptyPaymentException;
import com.devdream.model.Bill;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.GoldClient;
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

	public void processPayment(Sale sale, Client client, boolean isGoldClient, Commercial commercial)
			throws EmptyPaymentException, CantAffordException
	{
		if (sale.getSaleLines().isEmpty()) {
			throw new EmptyPaymentException();
		}
		
		boolean canAfford = true;
		double totalToPay;
		
		if (isGoldClient) {
			totalToPay = sale.getGoldDiscountedTotal();
			if (!client.canAffordPayment(totalToPay)) {
				canAfford = false;
			}
		}
		else {
			totalToPay = sale.getTotal();
			if (!client.canAffordPayment(totalToPay)) {
				canAfford = false;
			}
		}
		if (!canAfford) {
			throw new CantAffordException();
		}
		
		client.pay(totalToPay);
		
		commercial.increasePoints(sale.getSaleLines().size());
		
		sale.setSaleCurrentDate();

		if (client.getSpentCash() >= GoldClient.AMOUNT_FOR_GOLD_CLIENT) {
			GoldClient.convertToGoldClient(client);
		}
		
		Bill finalBill = new Bill(commercial, client, sale);
		Intent.getInstance().setCurrentBill(finalBill);
		
		super.changeView();
	}
	
}
