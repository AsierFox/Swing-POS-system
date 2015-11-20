package com.devdream.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OnViewChange implements ActionListener {

	private JFrame actualView
				 , newView;

	public OnViewChange(JFrame actualView, JFrame newView) {
		this.actualView = actualView;
		this.newView = newView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		changeView(newView);
	}
	
	private void changeView(JFrame newView) {
		actualView.dispose();
		actualView = newView;
	}
	
}