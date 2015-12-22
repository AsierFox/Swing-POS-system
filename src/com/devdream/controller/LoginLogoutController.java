package com.devdream.controller;

import javax.swing.JFrame;

import com.devdream.data.bind.Intent;
import com.devdream.model.Commercial;

/**
 * This controller manages the swapping between
 * two views, and the login and logout of a commercial.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class LoginLogoutController extends Controller {

	//
	// Constructor
	public LoginLogoutController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
	}

	//
	// Methods
	/**
	 * Login the commercial to the next JFrame view.
	 * @param comercial The commercial to login
	 */
	public void login(Commercial commercial) {
		Intent.getInstance().setLoguedCommercial(commercial);
		super.changeView();
	}
	
	/**
	 * Logout the commercial to the next JFrame view.
	 */
	public void logout() {
		super.changeView();
	}

}
