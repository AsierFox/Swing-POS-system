package com.devdream.helper;

/**
 * This class helps us to manage Math
 * operations.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class MathHelper {

	/**
	 * Gets the discount of a price.
	 * 
	 * @param discount Discount of the prince
	 * @param totalPrice Price
	 * @return
	 */
	public static float getDescuento(float discount, float totalPrice) {
		// TODO finish method
		return (totalPrice * discount);
	}
	
	/**
	 * Checks if a number is a customer has cash
	 * to deal with the total price.
	 * 
	 * @param num The number to check with
	 * @return If the client has cash for it, it returns true
	 */
	public static boolean hasClientCashForSale(double customerCash, long total) {
		return !(customerCash < total);
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
	
	/**
	 * Checks if a String is numerical.
	 * 
	 * @param string The string to check with
	 * @return Numeric or not
	 */
	public static boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
	
}