package com.devdream.model;

import com.devdream.helper.ErrorHandler;
import com.devdream.helper.MathHelper;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Service extends Product {

	private int horas;
	
	public Service(final int ID, String name, String description, float price, int horas) {
		super(ID, name, description, price);
		setHoras(horas);
	}
	
	//
	// Getters && Setters
	@Override
	public float getPrice() {
		return super.getPrice() * horas;
	}
	
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = (!MathHelper.isNegativeNumber(horas)) ?
				horas :
				0; // TODO Error
	}
	
}