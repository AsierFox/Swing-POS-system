package com.devdream.view.renderer;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.devdream.data.AppData;
import com.devdream.view.LoginView;

public class ViewRenderer {
	
	private JFrame window;

	public ViewRenderer(JFrame window) {
		this.window = window;
		window.setTitle(AppData.APP_TITLE);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource(AppData.ICON_PATH)));
		window.setSize(AppData.WIDTH, AppData.HEIGHT);
		window.setLocationRelativeTo(null); // Center the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void render() {
		window.setVisible(true);
	}
	
	public ImageIcon renderImage(String filePath) {
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(logo);
	}
	
}