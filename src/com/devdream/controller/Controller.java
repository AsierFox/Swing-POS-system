package com.devdream.controller;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.devdream.manager.ViewManager;

public abstract class Controller implements ActionListener {

	//
	// Attributes
	private JFrame actualView;
	private String newWindowName;
	private ViewManager viewManager;
	
	//
	// Constructors
	public Controller(JFrame actualView, String newWindowName) {
		this.actualView = actualView;
		this.newWindowName = newWindowName;
		viewManager = new ViewManager(actualView, newWindowName);
	}

	//
	// Getters && Setters
	public JFrame getActualView() {
		return actualView;
	}

	public String getNewWindowName() {
		return newWindowName;
	}
	
	public ViewManager getViewManager() {
		return viewManager;
	}
	
}