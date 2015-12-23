package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
public class LoginView extends View {

	private static final long serialVersionUID = 872752750081624433L;
	
	private static final String COMMERCIAL_ICON = "logincommercial.png";
	
	//
	// Attributes
	private JLabel commercialIconLabel;
	private MyComboBox<String, Commercial> commercialsComboBox;
	private JButton loginButton;
	private JButton exitButton;
	
	/** Load the data of the application data the first time. */
	static {
		DataGenerator data = new DataGenerator();
		data.load();
	}
	
	//
	// Constructors
	public LoginView() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		loadUI();

		loadListeners();
		
		render();
	}
	
	@Override
	protected void loadUI() {
		// Set Image to the panel
		JLabel logoImgLabel = new JLabel(renderImage(AppData.ImagePath.LOGO));
		logoImgLabel.setBounds(186, 11, 503, 324);
		add(logoImgLabel);
		
		// Login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		loginPanel.setBounds(269, 372, 299, 161);
		loginPanel.setLayout(null);
		add(loginPanel);
		
		// Commercial
		commercialsComboBox = new MyComboBox<String, Commercial>(Intent.getInstance().getCommercials());
		commercialsComboBox.setBounds(103, 61, 152, 27);
		loginPanel.add(commercialsComboBox);

		JLabel forCommercialLabel = new JLabel("Select comercial");
		forCommercialLabel.setBounds(102, 23, 153, 27);
		loginPanel.add(forCommercialLabel);
		forCommercialLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		commercialIconLabel = new JLabel(renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(20, 23, 73, 77);
		loginPanel.add(commercialIconLabel);
		
		// Exit button
		exitButton = new JButton("Exit");
		exitButton.setBounds(158, 117, 131, 33);
		loginPanel.add(exitButton);
		
		// Login button
		loginButton = new JButton("Login");
		loginButton.setBounds(20, 117, 127, 33);
		loginPanel.add(loginButton);
	}
	
	@Override
	protected void loadListeners() {
		loginButton.addActionListener((event) -> {
			LoginLogoutController loginController =
					new LoginLogoutController((Commercial) commercialsComboBox.getSelectedItem(), this, POSView.class.getName());
			loginController.login();
		});
		exitButton.addActionListener(new OnExitAction(this));
	}
	
}
