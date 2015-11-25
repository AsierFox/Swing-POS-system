package com.devdream.model;

import java.util.TreeMap;

/**
 * The shop of our application. Due to is a
 * single shop in all the application, we are
 * going to apply to it the Singleton design
 * pattern
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Shop {

	private static Shop i = null;
	
	/**
	 * The minimum salary of the commercials in the shop.
	 */
	public static float MINIMUN_SALARY = 750;
	
	/**
	 * All the products that the Shop has.
	 */
	private static TreeMap<Integer, Product> products;
	
	private Shop() {
		products = new TreeMap<Integer, Product>();
	}
	
	public static Shop getInstance() {
		if (i == null) {
			i = new Shop();
			return i;
		}
		return i;
	}
	
	/**
	 * Inserts an product to the shop.
	 * 
	 * @param id The id of the product
	 * @param product Product to add
	 */
	public Product addProduct(int id, Product product) {
		return products.put(id, product);
	}
	
	@Override
	public String toString() {
		return "Shop Products: " + products.toString();
	}

	/**
	 * Checks that has a product.
	 * 
	 * @param product Product to check
	 * @return The searched product
	 */
	public boolean hasProduct(Product product) { // TODO LOL ?
		return products.get(product) != null;
	}
	
	public Product getProduct(int id) {
		return products.get(id);
	}
	
}