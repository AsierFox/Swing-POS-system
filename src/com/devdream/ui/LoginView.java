package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.data.AppData;
import com.devdream.data.DataGenerator;
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
		getContentPane().setLayout(null);
		
		// Generate data
		DataGenerator data = new DataGenerator();
		data.load();
		
		// Set Image to the panel
		JLabel logoImgLabel = new JLabel(renderer.renderImage(AppData.ImagePath.LOGO));
		logoImgLabel.setBounds(120, 11, 503, 324);
		getContentPane().add(logoImgLabel);
		
		// Set commercial label and combo box
		JLabel forCommercialLabel = new JLabel("Comercial");
		forCommercialLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		forCommercialLabel.setBounds(319, 359, 80, 14);
		getContentPane().add(forCommercialLabel);
		
		MyComboBox<String, Commercial> commercialsComboBox = new MyComboBox<String, Commercial>(data.getCommercials());
		commercialsComboBox.setBounds(286, 384, 131, 23);
		getContentPane().add(commercialsComboBox);
		
		// Login btn
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener((event) -> {
			LoginLogoutController login = new LoginLogoutController(this, POSView.class.getName());
			login.login((Commercial) commercialsComboBox.getSelectedItem());
		});
		loginButton.setBounds(235, 442, 89, 23);
		getContentPane().add(loginButton);
		
		// Exit btn
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(368, 442, 89, 23);
		getContentPane().add(exitButton);
		
		renderer.render();
	}

}
