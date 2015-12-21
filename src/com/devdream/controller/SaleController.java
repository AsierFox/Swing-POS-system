package com.devdream.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.devdream.helper.StringHelper;
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
	public SaleController() {
		super();
		init();
	}

	public SaleController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
		init();
	}
	
	private void init() {
		sale = new Sale();
	}
	
	//
	// Methods
	public void newSale() {
		super.changeView();
	}
	
	public void addSaleLine(ShopOffer offer, int qty) {
		sale.addSaleLine(offer, qty);
	}
	
	public SaleLine deleteSaleSaleLine(int index) {
		return sale.deleteSaleLine(index);
	}
	
	//
	// Getters and Setters
	public Sale getSale() {
		return sale;
	}
	
	public ArrayList<SaleLine> getSaleLines() {
		return sale.getSaleLines();
	}
	
	public String getSaleSubtotal() {
		return StringHelper.formatAmount(sale.getSubtotal());
	}

	public String getSaleTax() {
		return StringHelper.formatAmount(sale.getTax());
	}

	public String getSaleTotal() {
		return StringHelper.formatAmount(sale.getTotal());
	}

}
