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

	private Commercial commercial;
	
	//
	// Constructor
	public LoginLogoutController(Commercial commercial, JFrame actualView, String newWindowName) {
		super(actualView, newWindowName);
		this.commercial = commercial;
	}

	//
	// Methods
	/**
	 * Login the commercial to the next JFrame view.
	 * @param comercial The commercial to login
	 */
	public void login() {
		Intent.getInstance().setLoguedCommercial(commercial);
		super.changeView();
	}
	
	/**
	 * Logout the commercial to the next JFrame view.
	 */
	public void logout() {
		Intent.getInstance().getCommercials().put(commercial.ID,
				new Commercial(commercial.ID, commercial.getName(), commercial.getSurname(), commercial.getSalary()));
		super.changeView();
	}

}
