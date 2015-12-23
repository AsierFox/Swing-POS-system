package com.devdream.ui;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.devdream.data.AppData;
import com.devdream.ui.custom.Alert;

/**
 * Abstract view class for all views. The purpose of this class
 * y to organize the view code separating different behaviors.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.1
 */
public abstract class View extends JFrame{

	private static final long serialVersionUID = 5509695633560856542L;
	
	//
	// Constructors
	public View() {
		super();
		setTitle(AppData.APP_TITLE);
		setIconImage();
		setSize(AppData.WIDTH, AppData.HEIGHT);
		setLocationRelativeTo(null); // Center the window
		setResizable(false);
	}
	
	//
	// Methods
	/** Renders an ImageIcon to show in the JLabel. */
	protected static ImageIcon renderImage(String filePath) {
		return new ImageIcon(View.class.getResource(filePath));
	}
	
	
	/** Renders the view. */
	protected void render() {
		setVisible(true);
	}
	
	/**
	 * Methods for loading the User Interface
	 * or the components of the view.
	 */
	protected abstract void loadUI();
	
	/**
	 * Load the listeners for the components
	 * of the view.
	 */
	protected abstract void loadListeners();

	/** Sets the Icon to the JFrame */
	private void setIconImage() {
		try {
			setIconImage(ImageIO.read(View.class.getResource(AppData.ImagePath.FAVICON)));
		} catch (IOException e) {
			Alert.showError(this, e.getMessage());
		}
	}
	
}
