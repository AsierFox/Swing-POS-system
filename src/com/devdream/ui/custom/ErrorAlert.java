package com.devdream.ui.custom;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * This class shows an error alert.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ErrorAlert extends JOptionPane {

	private static final long serialVersionUID = 4881699787962081552L;

	public static void show(Component c, String msg) {
		JOptionPane.showMessageDialog(c, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
