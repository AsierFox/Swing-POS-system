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

	private TreeMap<Integer, Product> products; // TODO Wrap product with services
	
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