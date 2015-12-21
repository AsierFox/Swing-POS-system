package com.devdream.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicInteger;

import com.devdream.model.Bill;
import com.devdream.model.SaleLine;

public class PrinterBuilder {

	private PrintJob printJob;
	private Graphics page;
	private AtomicInteger x;

	public PrinterBuilder() {
		printJob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Print", null);
		x = new AtomicInteger();
	}

	public void print(Bill bill) {
		int y = 65;
		
		page = printJob.getGraphics();
		page.setColor(Color.black);
		
		page.setFont(new Font("Dialog", Font.BOLD, 25));
		page.drawString("2Wheels sale", 200, nextLine());
		setTitleFont();
		page.drawString("Thanks for buying in our shop!", 180, nextLine());
		
		setTitleFont();
		page.drawString("Commercial", y, nextLine());
		setNormalFont();
		page.drawString("Name: " + bill.getCommercial().getName(), y, nextLine());
		page.drawString("Surname: " + bill.getCommercial().getSurname(), y, nextLine());
		
		setTitleFont();
		page.drawString("Client", y, nextLine());
		setNormalFont();
		page.drawString("Name: " + bill.getClient().getName(), y, nextLine());
		page.drawString("Surname: " + bill.getClient().getSurname(), y, nextLine());
		if (bill.getClient().isGoldClient()) {
			page.drawString("Gold Client", y, nextLine());
		}
		
		setTitleFont();
		page.drawString("Sale", y, nextLine());
		setNormalFont();
		page.drawString("Date: " + bill.getSale().getSaleDate(), y, nextLine());
		
		setTitleFont();
		page.drawString("Products", y, nextLine());
		setNormalFont();
		page.drawString("Name of the offer  x Quantity", y, nextLine());
		for (SaleLine sl : bill.getSale().getSaleLines()) {
			page.drawString(sl.getProduct().getName() + " x" + sl.getQuantity(), y, nextLine());
		}
		
		setTitleFont();
		page.drawString("Sale Total", y, nextLine());
		setNormalFont();
		page.drawString("Subtotal: " + bill.getSale().getFormatedSubtotal(), y, nextLine());
		page.drawString("Tax: " + bill.getSale().getFormatedTax(), y, nextLine());
		if (bill.getSale().getDiscountPercentage() > 0) {
			page.drawString("Discount of: " + bill.getSale().getDiscountPercentage() + "%", y, nextLine());
		}
		page.drawString("Total: " + bill.getSale().getFormatedTotal(), y, nextLine());;
		
		close();
	}
	
	private void close() {
		page.dispose();
		printJob.end();
	}

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
