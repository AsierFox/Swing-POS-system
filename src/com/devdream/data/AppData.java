package com.devdream.data;

/**
 * This class has general global variables for
 * the application
 * 
 * @author Asier Gonzalez
 * @version 1.1
 * @since 1.0
 */
public abstract class AppData {

	public static final String APP_TITLE = "2Wheels POS";
	public static final int WIDTH = 830, HEIGHT = 600;

	public static final String ASSETS_DIR = System.getProperty("user.dir") + "/assets/";
	
	/**
	 * Class that contains the path for specific image resources.
	 * 
	 * @author Asier Gonzalez
	 * @version 1.0
	 * @since 1.0
	 */
	public static class ImagePath {
		
		private static final String IMG_DIR = ASSETS_DIR + "img/";
		
		private static final String ICON_IMAGE = IMG_DIR + "icon/";
		
		public static final String FAVICON = IMG_DIR + "2wheels_favicon.png";
		public static final String LOGO = IMG_DIR + "logo/2wheels_logo.png";
		
		public static final String POS_ICON = ICON_IMAGE + "pos/";
		
	}
	
}
