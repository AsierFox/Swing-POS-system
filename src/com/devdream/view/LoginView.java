package com.devdream.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.devdream.controller.OnExitAction;
import com.devdream.controller.OnViewChange;
import com.devdream.data.AppData;
import com.devdream.view.custom.TextFieldPlaceHolder;
import com.devdream.view.renderer.ViewRenderer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File(AppData.LOGO_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(logo));
		picLabel.setBounds(82, 11, 250, 94);
		getContentPane().add(picLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(201, 117, 28, 20);
		getContentPane().add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new OnViewChange(this, new POSView()));
		btnLogin.setBounds(106, 180, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new OnExitAction(this));
		btnExit.setBounds(222, 180, 89, 23);
		getContentPane().add(btnExit);

		renderer.render();
	}
}