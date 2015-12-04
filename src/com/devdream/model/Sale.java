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
	private double tax;
	private double total;
	
	//
	// Constructor
	public Sale() {
		saleLines = new ArrayList<SaleLine>();
		saleDate = DateHelper.getCurrentDate();
		subtotal = .0d;
		tax = .0d;
		total = .0d;
	}
	
	public Sale(String saleDate, double subtotal, double tax, double total) {
		this();
		this.saleDate = saleDate;
		this.subtotal = subtotal;
		this.tax = tax;
		this.total = total;
	}
	
	//
	// Methods
	public void addSaleLine(ShopOffer offer, int quantity) {
		saleLines.add(new SaleLine(offer, quantity));
		addSubtotal(offer.getPrice(), quantity);
	}
	
	private void addSubtotal(double price, int quantity) {
		subtotal += (price * quantity);
	}
	
	//
	// Getters and Setters
	public void setSaleDate(int day, int month, int year) {
		saleDate = DateHelper.getCustomDate(day, month, year);
	}
	
	public String getSaleDate() {
		return saleDate;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public double getTax() {
		return tax;
	}

	public double getTotal() {
		return total;
	}
	
}
