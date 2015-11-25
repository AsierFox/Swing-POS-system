package com.devdream.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.devdream.controller.LoginController;
import com.devdream.controller.OnExitAction;
import com.devdream.data.AppData;
import com.devdream.data.gen.DataGenerator;

/**
 * It is the main window, it is displayed first to
 * authenticate the commercial
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class LoginView extends JFrame {

	private static final long serialVersionUID = 872752750081624433L;

	//
	// Constructors
	public LoginView() {
		ViewRenderer renderer = new ViewRenderer(this);
		getContentPane().setLayout(null);
		
		DataGenerator data = new DataGenerator();
		
		// Set Image to the panel
		JLabel picLabel = new JLabel(renderer.renderImage(AppData.LOGO_PATH));
		picLabel.setBounds(82, 11, 250, 94);
		getContentPane().add(picLabel);
		
		JComboBox<String> commercialsComboBox = new JComboBox<String>(data.getCommercialsComboBox());
		commercialsComboBox.setBounds(201, 117, 180, 40);
		getContentPane().add(commercialsComboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new LoginController(this, POSView.class.getName(), data.getCommercials(), commercialsComboBox));
		btnLogin.setBounds(106, 180, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new OnExitAction(this));
		btnExit.setBounds(222, 180, 89, 23);
		getContentPane().add(btnExit);
		
		renderer.render();
	}
}