package com.devdream.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.devdream.data.AppData;
import com.devdream.util.ViewRenderer;

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
	// Attributes
	private ViewRenderer renderer;

	public View() {
		super();
		renderer = new ViewRenderer(this);
		setTitle(AppData.APP_TITLE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppData.ImagePath.FAVICON));
		setSize(AppData.WIDTH, AppData.HEIGHT);
		setLocationRelativeTo(null); // Center the window
		setResizable(false);
	}
	
	//
	// Methods
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
	
	//
	// Getters and setters
	/** Returns the renderer. */
	protected ViewRenderer getRenderer() {
		return renderer;
	}
	
}
