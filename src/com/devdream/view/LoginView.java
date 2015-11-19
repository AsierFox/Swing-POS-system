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
import com.devdream.settings.Settings;
import com.devdream.view.custom.TextFieldPlaceHolder;

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
	
	private TextFieldPlaceHolder textField;

	public LoginView() {
		setTitle(Settings.APP_TITLE);
		setIconImage(new ImageIcon(Settings.ICON_PATH).getImage());
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setLocationRelativeTo(null); // Center the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set Panel
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		// Set Image to the panel
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File(Settings.LOGO_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel picLabel = new JLabel(new ImageIcon(logo));
		panel.add(picLabel);
		
		// Set TextBox for DNI Commercial
		textField = new TextFieldPlaceHolder("DNI");
		panel.add(textField);
		textField.setColumns(10);
		
		Button btnLogin = new Button("Login");
		panel.add(btnLogin);

		Button btnExit = new Button("Exit"); // TODO Apart Class
		btnExit.addActionListener(new OnExitAction(this));
		panel.add(btnExit);
		
		setVisible(true);
	}
	
}