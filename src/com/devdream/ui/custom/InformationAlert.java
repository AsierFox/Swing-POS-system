package com.devdream.ui.custom;

import java.awt.Component;

import javax.swing.JOptionPane;

public class InformationAlert {

	public static void show(Component c, String msg) {
		JOptionPane.showMessageDialog(c, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
