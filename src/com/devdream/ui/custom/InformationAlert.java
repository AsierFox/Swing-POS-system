package com.devdream.ui.custom;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * TODO Merge with error alert to a class Alert!
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class InformationAlert {

	public static void show(Component c, String msg) {
		JOptionPane.showMessageDialog(c, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
