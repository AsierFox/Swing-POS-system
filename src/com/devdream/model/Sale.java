package com.devdream.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.devdream.helper.DateHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Sale {

	//
	// Attributes
	private String saleDate;
	private ArrayList<SalesLine> saleLine;
	private double subtotal, total;
	
	//
	// Constructor

	//
	// Methods
	
	//
	// Getters and Setters
	public void setSaleDate(int day, int month, int year) {
		saleDate = DateHelper.getCustomDate(day, month, year);
	}
}