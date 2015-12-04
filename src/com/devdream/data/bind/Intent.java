package com.devdream.data.bind;

import java.util.ArrayList;

import com.devdream.data.SessionData;
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
			return i;
		}
		return i;
	}
	
	public void clearData() {
		sessionData = null;
	}
	
	//
	// Getters && Setters
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
		sessionData.getClients().add(c);
	}
	
	public ArrayList<Client> getClients() {
		return sessionData.getClients();
	}

	public void setClients(ArrayList<Client> clients) {
		sessionData.setClients(clients);
	}

	public ArrayList<Service> getServices() {
		return sessionData.getServices();
	}
	
	public ArrayList<Product> getProducts() {
		return sessionData.getProducts();
	}

	public void setServices(ArrayList<Service> services) {
		sessionData.setServices(services);
	}

	public void setProducts(ArrayList<Product> products) {
		sessionData.setProducts(products);
	}

}