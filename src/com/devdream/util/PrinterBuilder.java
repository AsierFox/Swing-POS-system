package com.devdream.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicInteger;

import com.devdream.model.Bill;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Sale;
import com.devdream.model.SaleLine;

/**
 * Class for printing the sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class PrinterBuilder {

	private PrintJob printJob;
	private Graphics page;
	private AtomicInteger x;

	public PrinterBuilder() {
		printJob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Print", null);
		x = new AtomicInteger(0);
	}

	public void print(Bill bill) throws NullPointerException {
		int y = 65;
		Commercial commercial = bill.getCommercial();
		Client client = bill.getClient();
		Sale sale = bill.getSale();
		
		page = printJob.getGraphics();
		page.setColor(Color.black);
		
		// Title
		page.setFont(new Font("Dialog", Font.BOLD, 25));
		page.drawString("2Wheels sale", 200, nextLine());
		setTitleFont();
		page.drawString("Thanks for buying in our shop!", 180, nextLine());

		// Commercial
		setTitleFont();
		page.drawString("Commercial", y, nextLine());
		setNormalFont();
		page.drawString("ID: " + commercial.ID, y, nextLine());
		page.drawString("Name: " + commercial.getName(), y, nextLine());
		page.drawString("Surname: " + commercial.getSurname(), y, nextLine());
		
		// Client
		setTitleFont();
		page.drawString("Client", y, nextLine());
		setNormalFont();
		page.drawString("Name: " + client.getName(), y, nextLine());
		page.drawString("Surname: " + client.getSurname(), y, nextLine());
		if (client.isGoldClient()) {
			page.drawString("Gold Client", y, nextLine());
		} else {
			if (client.getRemainingCashGoldClient() != 0) {
				page.drawString("Remaining for the Gold Client: " + client.getRemainingCashGoldClient(), y, nextLine());
			} else {
				page.drawString("You are now a Gold Client, enjoy your future discounts!", y, nextLine());
			}
		}

		// Sale
		setTitleFont();
		page.drawString("Sale", y, nextLine());
		setNormalFont();
		page.drawString("Date: " + sale.getSaleDate(), y, nextLine());
		
		if (!sale.getProductsFromSaleLines().isEmpty()) {
			setTitleFont();
			page.drawString("Products", y, nextLine());
			setNormalFont();
			page.drawString("Product  x Quantity", y, nextLine());
			for (SaleLine sl : sale.getProductsFromSaleLines()) {
				page.drawString(sl.getProduct().getName() + " x" + sl.getQuantity(), y, nextLine());
			}
		}

		if (!sale.getServicesFromSaleLines().isEmpty()) {
			setTitleFont();
			page.drawString("Services", y, nextLine());
			setNormalFont();
			page.drawString("Service x Quantity", y, nextLine());
			for (SaleLine sl : sale.getServicesFromSaleLines()) {
				page.drawString(sl.getProduct().getName() + " x" + sl.getQuantity(), y, nextLine());
			}
		}

		// Total
		setTitleFont();
		page.drawString("Sale Total", y, nextLine());
		setNormalFont();
		page.drawString("Subtotal: " + sale.getFormattedSubtotal(), y, nextLine());
		page.drawString("Tax: " + sale.getFormattedTax(), y, nextLine());
		if (sale.getDiscountPercentage() > 0) {
			page.drawString("Discount of: " + sale.getDiscountPercentage() + "%", y, nextLine());
		}
		page.drawString("Total: " + sale.getFormattedTotal(), y, nextLine());;
		
		close();
	}
	
	private void close() {
		page.dispose();
		printJob.end();
	}

	/** Converts the title font */
	private void setTitleFont() {
		page.setFont(new Font("Dialog", Font.BOLD, 14));
		x .addAndGet(10);
	}
	
	private void setNormalFont() {
		page.setFont(new Font("Dialog", Font.PLAIN, 10));
	}

	private int nextLine() {
		return x.addAndGet(20);
	}
	
}
