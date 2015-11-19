package com.devdream.model;

import java.util.TreeMap;

public class Shop {

	private TreeMap<Integer, Product> products;
	
	public Shop() {
		products = new TreeMap<Integer, Product>();
	}
	
	/**
	 * Inserts an product to the shop
	 * @param id The id of the product TODO PRIMERO EL TIPO O LA DESCRIPCION DEL PARAMETRO
	 * @param product Product
	 */
	public Product addProduct(int id, Product product) {
		return products.put(id, product);
	}
	
	public Product getProduct(int id) {
		return products.get(id);
	}
	
	/**
	 * Checks that has a product
	 * @param product Product to check
	 * @return The searched product
	 */
	public boolean hasProduct(Product product) {
		return products.get(product) != null;
	}
	
}