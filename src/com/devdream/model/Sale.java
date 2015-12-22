package com.devdream.model;

import java.util.ArrayList;
import java.util.Iterator;

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
	/**
	 * Add a sale line
	 * @param offer The offer to add to the sale
	 * @param quantity The quantity of the product
	 */
	public void addSaleLine(ShopOffer offer, int quantity) {
		addSubtotal(offer.getPrice(), quantity);
		saleLines.add(new SaleLine(offer, quantity));
	}
	
	/**
	 * Deletes the item from the ArrayList passing the index of it.
	 * @param index Index of the ArrayList
	 * @return
	 */
	public SaleLine deleteSaleLine(int index) {
		SaleLine deletedSaleLine = saleLines.get(index);
		decreaseSubtotal(deletedSaleLine.getProduct().getPrice(), deletedSaleLine.getQuantity());
		return saleLines.remove(index);
	}
	
	/** Add the sub total */
	private void addSubtotal(double price, int quantity) {
		subtotal += (price * quantity);
	}
	
	/** Decrease the sub total. */
	private void decreaseSubtotal(double price, int quantity) {
		subtotal -= (price * quantity);
	}
	
	/** Returns all the products from the specific offers from sale lines. */
	public ArrayList<SaleLine> getProductsFromSaleLines() {
		ArrayList<SaleLine> products = new ArrayList<>();
		Iterator<SaleLine> it = saleLines.iterator();
		while (it.hasNext()) {
			SaleLine currSaleLine = it.next();
			if (currSaleLine.getProduct() instanceof Product) {
				products.add(currSaleLine);
			}
		}
		return products;
	}
	
	/** Returns all the services from the specific offers from sale lines. */
	public ArrayList<SaleLine> getServicesFromSaleLines() {
		ArrayList<SaleLine> services = new ArrayList<SaleLine>();
		Iterator<SaleLine> it = saleLines.iterator();
		while (it.hasNext()) {
			SaleLine currSaleLine = it.next();
			if (currSaleLine.getProduct() instanceof Service) {
				services.add(currSaleLine);
			}
		}
		return services;
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
	
	public String getFormattedSubtotal() {
		return StringHelper.formatAmount(getSubtotal());
	}
	
	public double getTax() {
		return getSubtotal() * (Shop.VAT_TAX_PERCENTAGE / 100);
	}

	public String getFormattedTax() {
		return StringHelper.formatAmount(getTax());
	}
	
	private double getTotalWithoutDiscount() {
		return getSubtotal() + getTax();
	}
	
	public double getTotal() {
		return getTotalWithoutDiscount() - (getTotalWithoutDiscount() * (getDiscountPercentage() / 100));
	}
	
	public String getFormattedTotal() {
		return StringHelper.formatAmount(getTotal());
	}
	
}
