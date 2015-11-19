package com.devdream.model;

import java.util.HashMap;

public class Commercial extends User {

	private HashMap<Product, Integer> saledProducts;

	public Commercial(final String NIF) {
		super(NIF);
		// TODO Auto-generated constructor stub
	}

	private HashMap<Product, Integer> productosVendidos;
	
}