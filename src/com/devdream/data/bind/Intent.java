package com.devdream.data.bind;

import java.util.HashMap;

import com.devdream.data.SessionData;
import com.devdream.model.Bill;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Product;
import com.devdream.model.Service;

/**
 * This class is used to for data binding
 * between the different views.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Intent {

	private static Intent i = null;

	//
	// Attributes
	private SessionData sessionData;

	//
	// Constructors
	private Intent() {
		sessionData = new SessionData();
	}
	
	//
	// Methods
	public static Intent getInstance() {
		if (i == null) {
			i = new Intent();
		}
		return i;
	}
	
	public void clearData() {
		sessionData = null;
	}
	
	//
	// Getters && Setters
	public HashMap<String, Commercial> getCommercials() {
		return sessionData.getCommercials();
	}

	public void setCommercials(HashMap<String, Commercial> commercials) {
		sessionData.setCommercials(commercials);
	}
	
	public Commercial getLogedCommercial() {
		return sessionData.getCommercial();
	}
	
	public void setLoguedCommercial(Commercial commercial) {
		sessionData.setCommercial(commercial);
	}
	
	public Client getActualClient() {
		return sessionData.getClient();
	}
	
	public void setActualClient(Client c) {
		sessionData.setClient(c);
	}

	public void setNewClient(Client c) {
		sessionData.getClients().put(c.ID, c);
	}
	
	public Bill getCurrentBill() {
		return sessionData.getBill();
	}
	
	public void setCurrentBill(Bill bill) {
		sessionData.setBill(bill);
	}
	
	public HashMap<String, Client> getClients() {
		return sessionData.getClients();
	}

	public void setClients(HashMap<String, Client> clients) {
		sessionData.setClients(clients);
	}

	public HashMap<Integer, Service> getServices() {
		return sessionData.getServices();
	}
	
	public HashMap<Integer, Product> getProducts() {
		return sessionData.getProducts();
	}

	public void setServices(HashMap<Integer, Service> services) {
		sessionData.setServices(services);
	}

	public void setProducts(HashMap<Integer, Product> products) {
		sessionData.setProducts(products);
	}

}
