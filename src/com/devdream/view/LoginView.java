package com.devdream.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.devdream.controller.OnExitAction;
import com.devdream.data.AppData;
import com.devdream.data.bind.Intent;
import com.devdream.manager.ViewManager;
import com.devdream.view.renderer.ViewRenderer;


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
		
		// Set Image to the panel
		
		JLabel picLabel = new JLabel(renderer.renderImage(AppData.LOGO_PATH));
		picLabel.setBounds(82, 11, 250, 94);
		getContentPane().add(picLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(201, 117, 28, 20);
		getContentPane().add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ViewManager(this, POSView.class.getName(), true));
		btnLogin.setBounds(106, 180, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new OnExitAction(this));
		btnExit.setBounds(222, 180, 89, 23);
		getContentPane().add(btnExit);
		
		
		renderer.render();
	}
}