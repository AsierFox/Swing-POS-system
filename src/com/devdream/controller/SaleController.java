package com.devdream.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.devdream.model.Sale;
import com.devdream.model.SaleLine;

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
	
	/** Adds a sale line. */
	public void addSaleLine(SaleLine saleLine) {
		sale.addSaleLine(saleLine);
	}

	/** Deletes a sale line. */
	public SaleLine deleteSaleSaleLine(int index) {
		return sale.deleteSaleLine(index);
	}
	
	//
	// Getters and Setters
	public Sale getSale() {
		return sale;
	}
	
	/** Get all the current sale line collection. */
	public ArrayList<SaleLine> getSaleLines() {
		return sale.getSaleLines();
	}
	
	public String getSaleSubtotal() {
		return sale.getFormattedSubtotal();
	}

	public String getSaleTax() {
		return sale.getFormattedTax();
	}

	public String getSaleTotal() {
		return sale.getFormattedTotal();
	}

}
