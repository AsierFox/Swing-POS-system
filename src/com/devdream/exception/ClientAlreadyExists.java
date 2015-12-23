package com.devdream.exception;

/**
 * It is fired when a registered client already exists on the shop.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ClientAlreadyExists extends Exception {

	private static final long serialVersionUID = 7806458480954640103L;
	
	private static final String CUSTOMER_ALREADY_EXISTS = "The customer with that ID already exists.";
	
	@Override
	public String getMessage() {
		return CUSTOMER_ALREADY_EXISTS;
	}
	
}
