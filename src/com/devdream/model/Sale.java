package com.devdream.model;

import java.util.ArrayList;

import com.devdream.helper.DateHelper;

/**
 * The Sale class has Sales Line
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Sale {

	//
	// Attributes
	private ArrayList<SaleLine> saleLines;
	private String saleDate;
	private double subtotal;
	
	//
	// Constructor
	public Sale() {
		saleLines = new ArrayList<SaleLine>();
		saleDate = DateHelper.getCurrentDate();
		subtotal = .0d;
	}
	
	public Sale(ArrayList<SaleLine> saleLines, String saleDate, double subtotal) {
		this.saleLines = saleLines;
		this.saleDate = saleDate;
		this.subtotal = subtotal;
	}
	
	//
	// Methods
	public void addSaleLine(ShopOffer offer, int quantity) {
		addSubtotal(offer.getPrice(), quantity);
		saleLines.add(new SaleLine(offer, quantity));
	}
	
	public SaleLine deleteSaleLine(int index) {
		SaleLine deletedSaleLine = saleLines.get(index);
		decreaseSubtotal(deletedSaleLine.getProduct().getPrice(), deletedSaleLine.getQuantity());
		return saleLines.remove(index);
	}
	
	private void addSubtotal(double price, int quantity) {
		subtotal += (price * quantity);
	}
	
	private void decreaseSubtotal(double price, int quantity) {
		subtotal -= (price * quantity);
	}
	
	//
	// Getters and Setters
	public ArrayList<SaleLine> getSaleLines() {
		return saleLines;
	}
	
	public void setSaleCurrentDate() {
		saleDate = DateHelper.getCurrentDate();
	}
	
	public String getSaleDate() {
		return saleDate;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public double getTax() {
		return getSubtotal() * (Shop.VAT_TAX_PERCENTAGE / 100);
	}

	public double getTotal() {
		return getSubtotal() + getTax();
	}
	
	public double getGoldDiscountedTotal() {
		return getTotal() - (getTotal() * GoldClient.DISCOUNT_PERCENTAGE);
	}
	
}
