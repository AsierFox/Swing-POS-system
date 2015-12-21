package com.devdream.data;

import java.util.HashMap;

import com.devdream.data.bind.Intent;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.GoldClient;
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.model.SubscriberCard;

/**
 * Generates all the data needed for the application,
 * due to that we are not using a database.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class DataGenerator {

	//
	// Attributes
	private HashMap<String, Commercial> commercials;
	private HashMap<String, Client> clients;
	private HashMap<Integer, Product> products;
	private HashMap<Integer, Service> services;
	
	//
	// Constructors
	public DataGenerator() {		
		services = new HashMap<Integer, Service>();
		products = new HashMap<Integer, Product>();
		commercials = new HashMap<String, Commercial>();
		clients = new HashMap<String, Client>();

		Commercial c1 = new Commercial("1111111A", "Asier", 1000);
		Commercial c2 = new Commercial("2222222B", "Mary", 1000);
		Commercial c3 = new Commercial("3333333C", "Gogo", 1000);
		
		Client cl1 = new Client("123", "Anthony", new SubscriberCard(200));
		Client cl2 = new GoldClient("ASDASDADS", "Arika", new SubscriberCard(1000));
		Client cl3 = new Client("1221231", "Asier", new SubscriberCard(200));
		Client cl4 = new GoldClient("334243432", "Ibna", new SubscriberCard(1000));
		
		Product p1 = new Product(123123, "Chocolate", "UMMMM SWEET", 4.2f);
		Product p2 = new Product(123123, "Chocolate", "UMMMM SWEET", 4.2f);
		
		Service s1 = new Service(123345, "Repair This", "Fuck youu", 159.2f);
		Service s2 = new Service(435435, "Do all the stuff", "Roar!", 500);
		
		products.put(p1.getID(), p1);
		products.put(p2.getID(), p2);
		
		services.put(s1.getID(), s1);
		services.put(s2.getID(), s2);
		
		commercials.put(c1.ID, c1);
		commercials.put(c2.ID, c2);
		commercials.put(c3.ID, c3);
		
		clients.put(cl1.ID, cl1);
		clients.put(cl2.ID, cl2);
		clients.put(cl3.ID, cl3);
		clients.put(cl4.ID, cl4);
	}
	

	public void load() {
		Intent.getInstance().setClients(clients);
		Intent.getInstance().setServices(services);
		Intent.getInstance().setProducts(products);
	}
	
	//
	// Getters and Setters
	public HashMap<String, Commercial> getCommercials() {
		return commercials;
	}

}
