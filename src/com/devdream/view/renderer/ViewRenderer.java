package com.devdream.view.renderer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.devdream.data.AppData;

public class ViewRenderer {
	
	private JFrame window;

	public ViewRenderer(JFrame window) {
		this.window = window;
		window.setTitle(AppData.APP_TITLE);
		window.setIconImage(new ImageIcon(AppData.ICON_PATH).getImage());
		window.setSize(AppData.WIDTH, AppData.HEIGHT);
		window.setLocationRelativeTo(null); // Center the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.getContentPane().setLayout(null); // TODO ALL views absolute
	}
	
	public void render() {
		this.window.setVisible(true);
	}
	
}