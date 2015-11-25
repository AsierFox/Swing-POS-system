package com.devdream.helper;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorHandler {

	public static void showError(Component parentComponent, String errorTxt) {
		JOptionPane.showMessageDialog(parentComponent, errorTxt, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}