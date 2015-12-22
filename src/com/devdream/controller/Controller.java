package com.devdream.controller;

import javax.swing.JFrame;

/**
 * The controller abstract class. The controller is
 * the manage the classes connecting the model actions
 * that are called by a view. That is, it is used to
 * communicate between model classes and view.
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
	/** Switch between two JFrames. */
	protected final void changeView() {
		JFrame newView = null;
		actualView.dispose();
		try {
			newView = (JFrame) Class.forName(newWindowName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (newView != null)
			actualView = newView;
	}

	//
	// Setters and Getters
	/** Returns the actual view. */
	public JFrame getActualView() {
		return actualView;
	}
	
}
