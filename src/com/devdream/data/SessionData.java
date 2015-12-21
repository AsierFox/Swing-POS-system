package com.devdream.data;

import java.util.HashMap;

import com.devdream.model.Bill;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Product;
import com.devdream.model.Service;

/**
 * This class is contains data of the session, such as
 * the logged commercial, the current client and the offers
 * that are taking part in a sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class SessionData {

	//
	// Attributes
	private Commercial commercial;
	private Client client;
	private Bill bill;
	private HashMap<String, Client> clients;
	private HashMap<Integer, Service> services;
	private HashMap<Integer, Product> products;
	
	//
	// Constructors
	public SessionData() {
		commercial = null;
		client = null;
		clients = null;
		services = null;
		products = null;
	}
	
	//
	// Getters && Setters
	public Commercial getCommercial() {
		return commercial;
	}
	
	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public HashMap<String, Client> getClients() {
		return clients;
	}
	
	public void setClients(HashMap<String, Client> clients) {
		this.clients = clients;
	}

	public HashMap<Integer, Service> getServices() {
		return services;
	}
	
	public void setServices(HashMap<Integer, Service> services) {
		this.services = services;
	}
	
	public HashMap<Integer, Product> getProducts() {
		return products;
	}
	
	public void setProducts(HashMap<Integer, Product> products) {
		this.products = products;
	}

}
