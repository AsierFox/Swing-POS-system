package com.devdream.data;

import com.devdream.model.Commercial;

public class SessionData {

	public Commercial commercial;
	
	public SessionData(Commercial commercial) {
		this.commercial = commercial;
	}
	
	public Commercial getCommercial() {
		return commercial;
	}
	
}