package com.devdream.model;

public class GoldClient extends Client {

	//
	// Attributes
	private int descuento;
	
	//
	// Constructors
	public GoldClient(final String NIF) {
		super(NIF);
		// TODO Auto-generated constructor stub
	}
	
	public GoldClient(String NIF, String razonSocial, double saldoMes,
			double acumulado, int day, int month, int year) {
		super(NIF, razonSocial, saldoMes, acumulado, day, month, year);
		setDescuento(descuento);
	}

	//
	// Methods
	@Override
	public boolean comprar(Shop shop, Product product, int quantity) {
		double discountedPrice = product.getPrecio() * descuento;
		
		if ( discountedPrice > product.getPrecio() || !shop.hasProduct(product) ) return false;
		
		saldoMes -= product.getPrecio() - discountedPrice;
		productsAcquired.add(product);
		
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Descuento " + descuento;
	}
	
	//
	// Getters and Setters
	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		if (descuento == 0) this.descuento = 5;
		else this.descuento += descuento;
	}
	
}