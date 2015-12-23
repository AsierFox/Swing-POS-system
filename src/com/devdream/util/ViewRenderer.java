package com.devdream.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * This class renders the view configuring the JFrame component.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ViewRenderer {
	
	private JFrame window;

	public ViewRenderer(JFrame window) {
		this.window = window;
	}
	
	/** Renders the JFrame to display it. */
	public void render() {
		window.setVisible(true);
	}
	
	/**
	 * Renders the image passed by parameter.
	 * @param filePath The file path of the image
	 * @return Rendered image
	 */
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
