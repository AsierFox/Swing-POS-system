package com.devdream.exception;

public class CashNotValidException extends Exception {

	private static final long serialVersionUID = 6429868203022189222L;
	
	public static final String CASH_NOT_VALID = "Cash is not valid!";
	
	@Override
	public String getMessage() {
		return CASH_NOT_VALID;
	}
	
}
