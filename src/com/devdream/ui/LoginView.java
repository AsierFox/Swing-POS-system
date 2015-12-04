package com.devdream.ui;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.data.AppData;
import com.devdream.data.DataGenerator;
import com.devdream.data.bind.Intent;
import com.devdream.model.Commercial;
import com.devdream.ui.custom.MyComboBox;

/**
 * Login view of the application. It is going to login
 * the customer who is going to attend the clients.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class LoginView extends javax.swing.JFrame {

	private static final long serialVersionUID = 872752750081624433L;
	
	//
	// Constructors
	public LoginView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		setLayout(null);
		
		// Generate Data
		DataGenerator data = new DataGenerator();
		Intent.getInstance().setClients(data.getClients());
		Intent.getInstance().setServices(data.getServices());
		Intent.getInstance().setProducts(data.getProducts());
		
		// Set Image to the panel
		JLabel logoImgLabel = new JLabel(renderer.renderImage(AppData.LOGO_PATH));
		logoImgLabel.setBounds(82, 11, 250, 94);
		add(logoImgLabel);
		
		// Set commercial combobox
		MyComboBox<Commercial> commercialsComboBox = new MyComboBox<Commercial>(data.getCommercials());
		commercialsComboBox.setBounds(201, 117, 180, 40);
		add(commercialsComboBox);
		
		// Login btn
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener((event) -> {
			LoginLogoutController login = new LoginLogoutController(this, POSView.class.getName());
			login.login(commercialsComboBox.getItemAt(commercialsComboBox.getSelectedIndex()));
		});
		loginButton.setBounds(106, 180, 89, 23);
		add(loginButton);
		
		// Exit btn
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(222, 180, 89, 23);
		add(exitButton);
		
		// Show ui
		renderer.render();
	}
	
}