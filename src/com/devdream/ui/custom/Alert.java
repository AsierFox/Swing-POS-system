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
public class Alert extends JOptionPane {

	private static final long serialVersionUID = -1496885331548162635L;

	public static void showError(Component c, String msg) {
		showMessageDialog(c, msg, "Error", ERROR_MESSAGE);
	}
	
	public static void showInfo(Component c, String msg) {
		showMessageDialog(c, msg, "Information", INFORMATION_MESSAGE);
	}
	
}
