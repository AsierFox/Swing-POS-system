package com.devdream.controller;

import java.text.DecimalFormat;

import javax.swing.JFrame;

import com.devdream.model.Sale;
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
	// Contructors
	public SaleController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
		sale = new Sale();
	}
	
	public void addSaleLine(ShopOffer offer, int qty) {
		sale.addSaleLine(offer, qty);
	}
	
	public String getSaleSubtotal() {
		return new DecimalFormat("0.00").format(sale.getSubtotal());
	}
	
}
