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
import com.devdream.util.ViewRenderer;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

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
	
	private static final String COMMERCIAL_ICON = "commercial.png";
	
	//
	// Attributes
	private LoginLogoutController loginController;
	
	//
	// Constructors
	public LoginView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		// Create controller
		loginController = new LoginLogoutController(this, POSView.class.getName());
		
		// Generate data
		DataGenerator data = new DataGenerator();
		data.load();
		
		// Set Image to the panel
		JLabel logoImgLabel = new JLabel(renderer.renderImage(AppData.ImagePath.LOGO));
		logoImgLabel.setBounds(144, 11, 503, 324);
		getContentPane().add(logoImgLabel);
		
		// Login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loginPanel.setBounds(269, 372, 299, 161);
		loginPanel.setLayout(null);
		getContentPane().add(loginPanel);
		
		// Commercial
		MyComboBox<String, Commercial> commercialsComboBox = new MyComboBox<String, Commercial>(data.getCommercials());
		commercialsComboBox.setBounds(103, 61, 152, 27);
		loginPanel.add(commercialsComboBox);

		JLabel forCommercialLabel = new JLabel("Comercial");
		forCommercialLabel.setBounds(102, 23, 105, 27);
		loginPanel.add(forCommercialLabel);
		forCommercialLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JLabel commercialIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(20, 37, 68, 51);
		loginPanel.add(commercialIconLabel);
		
		// Exit button
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(158, 117, 131, 33);
		loginPanel.add(exitButton);
		exitButton.addActionListener(new OnExitAction(this));
		
		// Login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener((event) -> loginController.login((Commercial) commercialsComboBox.getSelectedItem()));
		loginButton.setBounds(20, 117, 127, 33);
		loginPanel.add(loginButton);
		
		renderer.render();
	}
	
}
