package com.devdream.exception;

/**
 * Occurs when a cash receives an invalid value.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class CashNotValidException extends Exception {

	private static final long serialVersionUID = 6429868203022189222L;
	
	public static final String CASH_NOT_VALID = "Cash is not valid!";
	
	@Override
	public String getMessage() {
		return CASH_NOT_VALID;
	}
	
}
