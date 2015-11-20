package com.devdream.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client extends User {

	//
	// Attributes
	protected double saldoMes;
	private Date fechaAlta;
	protected ArrayList<Product> productsAcquired;

	//
	// Constructors
	public Client(final String NIF) {
		super(NIF);
	}
	
	public Client(final String NIF, String razonSocial, double saldoMes,
						double acumulado, int day, int month, int year)
	{
		super(NIF);
		setSaldoMes(saldoMes);
		setFechaAlta(day, month, year);
		productsAcquired = new ArrayList<>();
	}

	//
	// Methods
	public boolean comprar(Shop shop, Product product, int quantity) {
		if ( !canBuy(product) || !shop.hasProduct(product) ) return false;
		
		saldoMes -= product.getPrecio();
		productsAcquired.add(product);
		
		return true;
	}
	
	private boolean canBuy(Product p) {
		return saldoMes < p.getPrecio();
	}
	
	@Override
	public String toString() {
		return super.toString();
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
		
		return fechaAlta;
	}

	public void setFechaAlta(int day, int month, int year) {
		try {
			this.fechaAlta = new SimpleDateFormat("dd/MM/yyyy")
					.parse(day + "/" + month + "/" + year);
		} catch (ParseException e) {
			System.out.println("La fecha indicada no es valida!");
		}
	}
	
	
}