package com.devdream.data;

import java.util.ArrayList;

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
	private ArrayList<Service> services;
	private ArrayList<Product> products;
	private ArrayList<Commercial> commercials;
	private ArrayList<Client> clients;
	
	//
	// Constructors
	public DataGenerator() {
		services = new ArrayList<Service>();
		products = new ArrayList<Product>();
		commercials = new ArrayList<Commercial>();
		clients = new ArrayList<Client>();

		products.add( new Product(123123, "Chocolate", "UMMMM SWEET", 4.2f) );
		products.add( new Product(123123, "Cake", "UUUUFF", 15.4f) );
		
		services.add( new Service(123345, "Repair This", "Fuck youu", 159.2f) );
		services.add( new Service(435435, "Do all the stuff", "Roar!", 500) );
		
		commercials.add( new Commercial("1111111A", "Asier", 1000) );
		commercials.add( new Commercial("2222222B", "Mary", 1000) );
		commercials.add( new Commercial("3333333C", "Gogo", 1000) );

		clients.add( new Client("QWEEWQEWQ", "Anthony", new SubscriberCard(200)) );
		clients.add( new GoldClient("ASDASDADS", "Arika", new SubscriberCard(1000)) );
	}
	
	//
	// Getters && Setters
	public ArrayList<Service> getServices() {
		return services;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public ArrayList<Commercial> getCommercials() {
		return commercials;
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}

}
