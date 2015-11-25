package com.devdream.manager;

import javax.swing.JFrame;

public class ViewManager {

	private JFrame actualView;
	private String newWindowName;

	public ViewManager(JFrame actualView, String newWindowName){
		this.actualView = actualView;
		this.newWindowName = newWindowName;
	}
	
	public void changeView() {
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