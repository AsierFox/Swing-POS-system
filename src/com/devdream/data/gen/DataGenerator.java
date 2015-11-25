package com.devdream.data.gen;

import java.util.ArrayList;
import java.util.Iterator;

import com.devdream.model.Commercial;
import com.devdream.model.Product;
import com.devdream.model.Shop;
import com.devdream.model.SubscriberCard;

/**
 * Generates all the data needed for the application,
 * due to that we are not using a database
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class DataGenerator {

	private ArrayList<Product> products;
	private ArrayList<Commercial> commercials;
	
	public DataGenerator() {
		products = new ArrayList<Product>();
		commercials = new ArrayList<Commercial>();
		
		products.add( new Product(123123, "Chocolate", "UMMMM SWEET", 4.2f) );
		commercials.add( new Commercial("1111111A", "Asier", new SubscriberCard(100), 1000) );
		commercials.add( new Commercial("2222222B", "Mary", new SubscriberCard(100), 1000) );
		commercials.add( new Commercial("3333333C", "Gogo", new SubscriberCard(100), 1000) );
		
		fillShop();
	}

	private void fillShop() {
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product actualProduct = it.next();
			Shop.getInstance().addProduct(actualProduct.getID(), actualProduct);
		}
	}
	
	public ArrayList<Commercial> getCommercials() {
		return commercials;
	}
	
	public String[] getCommercialsComboBox() {
		String[] commercialIDs = new String[commercials.size()];
		int i = 0;
		Iterator<Commercial> it = commercials.iterator();
		while (it.hasNext()) {
			Commercial c = it.next();
			commercialIDs[i++] = c.ID + " [" + c.getName() + "]";
		}
		return commercialIDs;
	}
	
}