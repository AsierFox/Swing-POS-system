package com.devdream.exception;

/**
 * Occurs when a commercial wants to proceed the payment
 * with any offer in the sale line.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class EmptyPaymentException extends Exception {

	private static final long serialVersionUID = 3299336489388748183L;
	
	public static final String NO_OFFERS_TO_SALE = "No offers to process the payment!";

	@Override
	public String getMessage() {
		return NO_OFFERS_TO_SALE;
	}
	
}
