package com.devdream.ui.custom;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * This class shows alerts using JOptions Pane class.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Alert {

	public static void showError(Component c, String msg) {
		JOptionPane.showMessageDialog(c, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInfo(Component c, String msg) {
		JOptionPane.showMessageDialog(c, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
