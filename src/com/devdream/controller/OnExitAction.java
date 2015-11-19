package com.devdream.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Sets a behaviour when the users wants to exit
 * from the application
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class OnExitAction implements ActionListener {
	
	private Component component;

	public OnExitAction(Component component) {
		this.component = component;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (wantsExit()) {
			((JFrame) component).dispose();
		}
	}
	
	private boolean wantsExit() {
		return JOptionPane.showConfirmDialog(component,
					"Are you sure you want to exit?",
					"Exit",
					JOptionPane.YES_OPTION) == 0;
	}

}