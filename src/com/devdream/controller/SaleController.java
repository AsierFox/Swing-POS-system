package com.devdream.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.devdream.model.Sale;
import com.devdream.model.SaleLine;
import com.devdream.model.ShopOffer;

/**
 * Controller for the sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class SaleController extends Controller {

	//
	// Attributes
	private Sale sale;
	
	//
	// Constructors
	public SaleController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
		sale = new Sale();
	}
	
	//
	// Methods
	public void addSaleLine(ShopOffer offer, int qty) {
		sale.addSaleLine(offer, qty);
	}
	
	public SaleLine deleteSaleSaleLine(int index) {
		return sale.deleteSaleLine(index);
	}
	
	private String formatAmount(double amount) {
		return new DecimalFormat("0.00").format(amount);
	}
	
	//
	// Getters and Setters
	public ArrayList<SaleLine> getSaleLines() {
		return sale.getSaleLines();
	}
	
	public String getSaleSubtotal() {
		return formatAmount(sale.getSubtotal());
	}

	public String getSaleTax() {
		return formatAmount(sale.getTax());
	}

	public String getSaleTotal() {
		return formatAmount(sale.getTotal());
	}

}
