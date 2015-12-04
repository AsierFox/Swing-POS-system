package com.devdream.model;

/**
 * The Bill is all the information about
 * the sale. The customer that purchased
 * the sale, the commercial who TODO vendio,
 * and the date when was realized.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Bill {

	//
	// Attributes
	private Commercial commercial;
	private Client client;
	private Sale sale;
	private String saleDate;

	//
	// Constructor
	public Bill(Commercial commercial, Client client, Sale sale, String saleDate) {
		this.commercial = commercial;
		this.client = client;
		this.sale = sale;
		this.saleDate = saleDate;
	}
	
	//
	// Methods
	
	// TODO Generate PDF and Printer
	
	//
	// Getters and Setters
	public Commercial getCommercial() {
		return commercial;
	}

	public Client getClient() {
		return client;
	}

	public Sale getSale() {
		return sale;
	}

	public String getSaleDate() {
		return saleDate;
	}
	
}