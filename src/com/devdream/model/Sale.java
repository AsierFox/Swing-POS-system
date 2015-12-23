package com.devdream.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.devdream.util.DateHelper;
import com.devdream.util.StringHelper;

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
	public void addSaleLine(SaleLine saleLine) {
		int indexRepeated = checkOfferAlreadyInSale(saleLine.getOffer());
		if (indexRepeated > -1) {
			SaleLine repeatedSaleLine = saleLines.get(indexRepeated);
			repeatedSaleLine.setQuantity(repeatedSaleLine.getQuantity() + saleLine.getQuantity());
		} else {
			saleLines.add(saleLine);
		}
		addSubtotal(saleLine.getOffer().getPrice(), saleLine.getQuantity());
	}
	
	/**
	 * Checks if the saline is already on the sale.
	 * @return Returns a number greater than -1 if the offer is not already on the sale line
	 */
	private int checkOfferAlreadyInSale(ShopOffer offer) {
		for (int i = 0; i < saleLines.size(); ++i) {
			if (saleLines.get(i).getOffer().ID == offer.ID) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Deletes the item from the ArrayList passing the index of it.
	 * @param index Index of the ArrayList
	 * @return
	 */
	public SaleLine deleteSaleLine(int index) {
		SaleLine deletedSaleLine = saleLines.get(index);
		decreaseSubtotal(deletedSaleLine.getOffer().getPrice(), deletedSaleLine.getQuantity());
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
			if (currSaleLine.getOffer().isProduct()) {
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
			if (currSaleLine.getOffer().isService()) {
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
		return StringHelper.formatAmount(getSubtotal()) + Shop.COIN_BADGE;
	}
	
	public double getTax() {
		return getSubtotal() * (Shop.VAT_TAX_PERCENTAGE / 100);
	}

	public String getFormattedTax() {
		return StringHelper.formatAmount(getTax()) + Shop.COIN_BADGE;
	}
	
	private double getTotalWithoutDiscount() {
		return getSubtotal() + getTax();
	}
	
	public double getTotal() {
		return getTotalWithoutDiscount() - (getTotalWithoutDiscount() * (getDiscountPercentage() / 100));
	}
	
	public String getFormattedTotal() {
		return StringHelper.formatAmount(getTotal()) + Shop.COIN_BADGE;
	}
	
}
