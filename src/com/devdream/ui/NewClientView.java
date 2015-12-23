package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.SubscribeClientController;
import com.devdream.data.AppData;
import com.devdream.exception.CashFormatException;
import com.devdream.exception.ClientAlreadyExists;
import com.devdream.exception.TextNotValidException;
import com.devdream.ui.custom.Alert;

/**
 * This view shows the components for subscribing a new client.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class NewClientView extends View {

	private static final long serialVersionUID = 6745170097521269127L;
	
	//
	// Global
	private static final String NEW_CLIENT_ICON = "newclient.png";
	
	//
	// Attributes
	private JCheckBox goldClientCheckBox;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField subscriberCardCashTextField;
	private JTextField surnameTextField;
	private JButton subscribeClientButton;
	private JButton closeButton;
	
	//
	// Constructors
	public NewClientView() {
		super();
		setSize(420, 320);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		loadUI();
		
		loadListeners();
		
		render();
	}

	//
	// Methods
	/** Clear the data inserted by the user of the components */
	private void clearData() {
		idTextField.setText("");
		nameTextField.setText("");
		subscriberCardCashTextField.setText("");
		goldClientCheckBox.setSelected(false);
	}

	@Override
	protected void loadUI() {
		// New client label
		JLabel newClientLabel = new JLabel("New Client");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		newClientLabel.setBounds(143, 21, 126, 25);
		add(newClientLabel);
		
		JLabel newClientIconLabel = new JLabel(renderImage(AppData.ImagePath.POS_ICON + NEW_CLIENT_ICON));
		newClientIconLabel.setBounds(254, 49, 39, 35);
		add(newClientIconLabel);
		
		// Client ID
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forIdLabel.setBounds(49, 49, 46, 14);
		add(forIdLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(49, 74, 114, 20);
		add(idTextField);
		idTextField.setColumns(10);
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forNameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forNameLabel.setBounds(49, 105, 46, 14);
		add(forNameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(49, 130, 114, 20);
		add(nameTextField);
		
		// Client Surname
		surnameTextField = new JTextField();
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(49, 179, 114, 20);
		add(surnameTextField);
		
		JLabel forSurnameLabel = new JLabel("Surname");
		forSurnameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forSurnameLabel.setBounds(49, 154, 77, 14);
		add(forSurnameLabel);
		
		// Client card
		JLabel forSubscriberCardCashLabel = new JLabel("Subscriber card initial cash");
		forSubscriberCardCashLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forSubscriberCardCashLabel.setBounds(216, 105, 178, 14);
		add(forSubscriberCardCashLabel);
		
		subscriberCardCashTextField = new JTextField();
		subscriberCardCashTextField.setColumns(10);
		subscriberCardCashTextField.setBounds(216, 130, 97, 20);
		add(subscriberCardCashTextField);
		
		// Gold client
		JLabel forGoldClientLabel = new JLabel("Set as Gold Client");
		forGoldClientLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forGoldClientLabel.setBounds(216, 154, 114, 14);
		add(forGoldClientLabel);
		
		goldClientCheckBox = new JCheckBox("Gold Client");
		goldClientCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 11));
		goldClientCheckBox.setBounds(216, 177, 97, 23);
		add(goldClientCheckBox);
		
		// Subscribe button
		subscribeClientButton = new JButton("Subscribe client");	
		subscribeClientButton.setBounds(49, 219, 142, 35);
		add(subscribeClientButton);
		
		// Close button
		closeButton = new JButton("Close");
		closeButton.setBounds(216, 219, 142, 35);
		add(closeButton);
	}

	@Override
	protected void loadListeners() {
		subscribeClientButton.addActionListener((e) -> {
			SubscribeClientController clientSubscriber = new SubscribeClientController();
			try {
				clientSubscriber.subscribeClient(idTextField.getText(),
					nameTextField.getText(),
					surnameTextField.getText(),
					subscriberCardCashTextField.getText(),
					goldClientCheckBox.isSelected());
				Alert.showInfo(this, "Client Subscribed successfully!");
				clearData();
			} catch(CashFormatException | TextNotValidException | ClientAlreadyExists err) {
				Alert.showError(this, err.getMessage());
			}
		});

		closeButton.addActionListener((e) -> this.dispose());
	}
	
}
