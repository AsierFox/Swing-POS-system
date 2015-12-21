package com.devdream.model;

import java.util.ArrayList;

import com.devdream.helper.DateHelper;
import com.devdream.helper.StringHelper;

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
	private float discountPercentage;
	
	//
	// Constructor
	public Sale() {
		saleLines = new ArrayList<SaleLine>();
		saleDate = DateHelper.getCurrentDate();
		subtotal = .0d;
		discountPercentage = .0f;
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
	
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	
	public void setDiscountPercentage(float discount) {
		this.discountPercentage = discount;
	}
	
	public String getSaleDate() {
		return saleDate;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public String getFormatedSubtotal() {
		return StringHelper.formatAmount(getSubtotal());
	}
	
	public double getTax() {
		return getSubtotal() * (Shop.VAT_TAX_PERCENTAGE / 100);
	}

	public String getFormatedTax() {
		return StringHelper.formatAmount(getTax());
	}
	
	private double getTotalWithoutDiscount() {
		return getSubtotal() + getTax();
	}
	
	public double getTotal() {
		return getTotalWithoutDiscount() - (getTotalWithoutDiscount() * (getDiscountPercentage() / 100));
	}
	
	public String getFormatedTotal() {
		return StringHelper.formatAmount(getTotal());
	}
	
}
