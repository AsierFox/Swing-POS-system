package com.devdream.data;

import java.util.ArrayList;

import com.devdream.model.Client;
import com.devdream.model.Commercial;

public class SessionData {

	public Commercial commercial;
	public ArrayList<Client> clients;
	// TODO Bill history
	
	public SessionData(Commercial commercial) {
		this.commercial = commercial;
	}
	
	public Commercial getCommercial() {
		return commercial;
	}
	
}