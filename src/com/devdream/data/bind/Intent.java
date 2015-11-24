package com.devdream.data.bind;

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
	}
	
}