package com.devdream.exception;

/**
 * Occurs when a client has not enough money to
 * afford the payment of the sale.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class CantAffordException extends Exception {

	private static final long serialVersionUID = 8345911921988987203L;
	
	public static final String CLIENT_HAS_NOT_CASH = "The client can not afford the payment!";
	
	@Override
	public String getMessage() {
		return CLIENT_HAS_NOT_CASH;
	}
	
}
