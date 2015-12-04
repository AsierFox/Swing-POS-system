package com.devdream.ui;

import javax.swing.JFrame;

public class NewClientView extends JFrame {

	private static final long serialVersionUID = 6745170097521269127L;
	
	public NewClientView() {
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		setLayout(null);
		
		
		
		renderer.render();
	}
	
}