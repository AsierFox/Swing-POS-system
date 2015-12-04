package com.devdream.controller;

import javax.swing.JFrame;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public abstract class Controller {

	//
	// Attributes
	private JFrame actualView;
	private String newWindowName;
	
	//
	// Constructors
	public Controller() {
		actualView = null;
		newWindowName = null;
	}
	
	public Controller(JFrame actualView, String newWindowName) {
		this.actualView = actualView;
		this.newWindowName = newWindowName;
	}
	
	//
	// Methods
	protected final void changeView() {
		actualView.dispose();
		
		JFrame newView = null;
		try {
			newView = (JFrame) Class.forName(newWindowName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (newView != null) {
			actualView = newView;
		}
	}

	//
	// Setters and Getters
	public JFrame getActualView() {
		return actualView;
	}
	
}