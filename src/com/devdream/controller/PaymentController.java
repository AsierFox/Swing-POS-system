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

	/**
	 * Process of the payment.
	 * @param sale The sale
	 * @param client The client
	 * @param isGoldClient If the client is a gold client
	 * @param commercial The commercial
	 * @throws EmptyPaymentException
	 * @throws CantAffordException
	 */
	public void processPayment(Sale sale, Client client, boolean isGoldClient, Commercial commercial)
			throws EmptyPaymentException, CantAffordException
	{
		if (sale.getSaleLines().isEmpty()) {
			throw new EmptyPaymentException();
		}
		if (isGoldClient) {
			sale.setDiscountPercentage(GoldClient.DISCOUNT_PERCENTAGE);
		}
		if (!client.canAffordPayment(sale.getTotal())) {
			throw new CantAffordException();
		}
		
		client.pay(sale.getTotal());
		
		commercial.increasePoints(sale.getSaleLines().size());
		commercial.increaseSalary();
		
		sale.setSaleCurrentDate();

		if (client.getSpentCash() >= GoldClient.AMOUNT_FOR_GOLD_CLIENT) {
			GoldClient.convertToGoldClient(client);
		}
		
		Bill finalBill = new Bill(commercial, client, sale);
		Intent.getInstance().setCurrentBill(finalBill);
		
		super.changeView();
	}
	
}
