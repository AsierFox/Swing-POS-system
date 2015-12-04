package com.devdream.model;

import java.util.TreeMap;

/**
 * The shop of our application. Due to is a
 * single shop in all the application, we are
 * going to apply to it the Singleton design
 * pattern.
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
	 * 
	 */
	public static float VAT_TAX_PERCENTAGE = 16;
	/**
	 * All the products that the Shop has.
	 */
	private static TreeMap<Integer, ShopOffer> products;
	
	private Shop() {
		products = new TreeMap<Integer, ShopOffer>();
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
	public ShopOffer addProduct(int id, ShopOffer product) {
		return products.put(id, product);
	}
	
	@Override
	public String toString() {
		return "Shop Products: " + products.toString();
	}
	
	public ShopOffer getProduct(int id) {
		return products.get(id);
	}
	
	/**
	 * Checks if the client passed by parameter is a GoldClient.
	 * 
	 * @param client The Client object type to check with.
	 * @return If the client is a GoldClient.
	 */
	public static boolean isGoldClient(Client client) {
		return client instanceof GoldClient;
	}
	
}