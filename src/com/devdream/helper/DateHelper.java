package com.devdream.helper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This class is the helper for date management.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class DateHelper {

	public static String getCustomDate(int day, int month, int year) {
		return LocalDate.of(year, month, day).format(DateTimeFormatter.ofPattern("d/MM/uuuu"));
	}
	
	public static String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
}
