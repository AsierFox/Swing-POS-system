package com.devdream.data;

/**
 * This class has general global variables for
 * the application
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public abstract class AppData {

	public static final String APP_TITLE = "2Wheels POS";

	public static final int WIDTH = 800, HEIGHT = 600;
	
	public static final String ASSETS_DIR = System.getProperty("user.dir") + "/assets/";
	public static final String IMG_DIR_PATH = ASSETS_DIR + "img/";
	
	private static final String FAVICON_IMG = "2wheels_favicon.png";
	public static final String FAVICON_PATH = IMG_DIR_PATH + "icon/" + FAVICON_IMG;
	
	private static final String LOGO_IMG = "2wheels_logo.png";
	public static final String LOGO_PATH = IMG_DIR_PATH + "logo/" + LOGO_IMG;
	
	public static final String POS_ICON_PATH = IMG_DIR_PATH + "pos_icon/";
	
}
