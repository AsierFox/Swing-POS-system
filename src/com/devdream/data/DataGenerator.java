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

		Commercial c1 = new Commercial("1111111A", "Asier", "Gonzalez", 1000);
		Commercial c2 = new Commercial("2222222B", "Mary", "Desmond", 6000);
		Commercial c3 = new Commercial("3333333C", "Andoni", "Davila", 2000);
		Commercial c4 = new Commercial("4444444D", "Maren", "Goti", 2000);
		
		Client cl1 = new Client("342423432A", "Anthony", "Algora", new SubscriberCard(400));
		Client cl2 = new GoldClient("54354353B", "Arika", "Atami", new SubscriberCard(2000));
		Client cl3 = new Client("123", "Iratxe", "Aguilera", new SubscriberCard(100));
		Client cl4 = new GoldClient("334243432", "Danny", "Gonzalez", new SubscriberCard(650));
		
		Product p1 = new Product(123123, "Chocolate", "The best chocolate", 4.2f);
		Product p2 = new Product(123123, "Chocolate", "UMMMM SWEET", 4.2f);
		
		Service s1 = new Service(123345, "Repair This", "Fuck youu", 159.2f);
		Service s2 = new Service(435435, "Do all the stuff", "Roar!", 500);
		
		commercials.put(c1.ID, c1);
		commercials.put(c2.ID, c2);
		commercials.put(c3.ID, c3);
		commercials.put(c4.ID, c4);
		
		clients.put(cl1.ID, cl1);
		clients.put(cl2.ID, cl2);
		clients.put(cl3.ID, cl3);
		clients.put(cl4.ID, cl4);
		
		products.put(p1.getID(), p1);
		products.put(p2.getID(), p2);
		
		services.put(s1.getID(), s1);
		services.put(s2.getID(), s2);
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
