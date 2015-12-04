package com.devdream.model;

import java.util.ArrayList;

import com.devdream.helper.DateHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Sale {

	//
	// Attributes
	private String saleDate;
	private ArrayList<SalesLine> saleLine;
	private double subtotal, total;
	// TODO Calculate the total using the VAT_TAX from shop to calculate the total
	
	//
	// Constructor
	public Sale(ArrayList<SalesLine> saleLine, String saleDate, double subtotal, double total) {
	}
	
	//
	// Methods
	
	//
	// Getters and Setters
	public void setSaleDate(int day, int month, int year) {
		saleDate = DateHelper.getCustomDate(day, month, year);
	}
}