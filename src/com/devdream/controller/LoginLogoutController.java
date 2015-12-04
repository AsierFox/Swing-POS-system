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

	public LoginLogoutController(JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
	}
	
	public void login(Commercial comercial) {
		Intent.getInstance().setLoguedCommercial(comercial);
		super.changeView();
	}
	
	public void logout() {
		super.changeView();
	}

}
