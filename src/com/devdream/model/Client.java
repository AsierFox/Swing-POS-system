package com.devdream.model;

import java.util.Date;

import com.devdream.helper.DateHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Client extends User {

	//
	// Attributes
	protected double saldoMes;
	private String joinedDate;

	//
	// Constructors
	public Client(final String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
	}
	
	//
	// Methods
	public final boolean comprar(Product product, int quantity) {
		return false;
	}

	//
	// Getters and Setters
	public String getID() {
		return ID;
	}

	public double getSaldoMes() {
		return saldoMes;
	}

	public void setSaldoMes(double saldoMes) {
		this.saldoMes = saldoMes;
	}

	public String getFechaAlta() {
		return joinedDate;
	}

	public void setFechaAlta(int day, int month, int year) {
		this.joinedDate = DateHelper.getCustomDate(day, month, year);
	}
	
}