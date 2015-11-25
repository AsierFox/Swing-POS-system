package com.devdream.data.bind;

import com.devdream.data.SessionData;
import com.devdream.model.Commercial;

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
	
	private SessionData sessionData;
	
	private Intent() {
	}
	
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
	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}
	
	public Commercial getCommercial() {
		return sessionData.getCommercial();
	}
	
}