package com.devdream.main;

import javax.swing.SwingUtilities;

import com.devdream.ui.LoginView;

/**
 * Main class, which is going to launch
 * the whole application.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LoginView());
	}

}
