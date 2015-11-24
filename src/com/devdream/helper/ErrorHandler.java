package com.devdream.helper;

/**
 * Helper class that contains static methods
 * for helping to check errors.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ErrorHandler {
	
	/**
	 * Checks if a number is a customer has cash
	 * to TODO #afrontar# the total price.
	 * 
	 * @param num The number to check with
	 * @return Negative or not
	 */
	public static boolean hasCustomerCashForSale(double customerCash, long total) {
		return !(customerCash < total);
	}
	
	/**
	 * Checks if a String is empty or null.
	 * 
	 * @param string The string to check the emptiness
	 * @return Empty or not
	 */
	public static boolean isStringNull(String s) {
		s = s.trim();
		return s != null && s.isEmpty();
	}
	
	/**
	 * Checks if a String is numerical.
	 * 
	 * @param string The string to check with
	 * @return Numeric or not
	 */
	public static boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Checks if a number is negative.
	 * 
	 * @param num The number to check with
	 * @return Negative or not
	 */
	public static boolean isNegativeNumber(int num) {
		return num < 0;
	}

}