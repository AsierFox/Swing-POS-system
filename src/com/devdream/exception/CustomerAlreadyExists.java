package com.devdream.exception;

public class CustomerAlreadyExists extends Exception {

	private static final long serialVersionUID = 7806458480954640103L;
	
	private static final String CUSTOMER_ALREADY_EXISTS = "The customer with that ID already exists.";
	
	@Override
	public String getMessage() {
		return CUSTOMER_ALREADY_EXISTS;
	}
	
}
