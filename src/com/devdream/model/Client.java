package com.devdream.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client extends User {

	//
	// Attributes
	protected double saldoMes;
	private Date joinedDate;
	protected ArrayList<Product> productsAcquired;

	//
	// Constructors
	public Client(final String ID, String name, SubscriberCard subscriberCard) {
		super(ID, name, subscriberCard);
	}
	
	//
	// Methods
	public final boolean comprar(Shop shop, Product product, int quantity) {
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

	public Date getFechaAlta() {
		return joinedDate;
	}

	public void setFechaAlta(int day, int month, int year) {
		try {
			this.joinedDate = new SimpleDateFormat("dd/MM/yyyy")
					.parse(day + "/" + month + "/" + year);
		} catch (ParseException e) {
			System.out.println("La fecha indicada no es valida!");
		}
	}

	public boolean comprar(Product product, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}