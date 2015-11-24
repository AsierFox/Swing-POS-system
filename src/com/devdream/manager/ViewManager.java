package com.devdream.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ViewManager implements ActionListener {

	private JFrame actualView;
	private String newWindowName;
	private boolean restricted;

	public ViewManager(JFrame actualView, String newWindowName, boolean restricted){
		this.actualView = actualView;
		this.newWindowName = newWindowName;
		this.restricted = restricted;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!restricted) {
			changeView();
		}
		else {
			// TODO parse to login commercial and change the view
		}
	}
	
	private void changeView() {
		actualView.dispose();
		
		JFrame newView = null;
		try {
			newView = (JFrame) Class.forName(newWindowName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace(); // TODO Control exception
		}
		if (newView != null) {
			actualView = newView;
		}
	}
	
}