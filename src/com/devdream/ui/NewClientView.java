package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.SubscribeClientController;
import com.devdream.exception.CashFormatException;
import com.devdream.exception.TextNotValidException;
import com.devdream.ui.custom.Alert;

/**
 * This view shows the components for subscribing a new client.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class NewClientView extends JFrame {

	private static final long serialVersionUID = 6745170097521269127L;
	
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField subscriberCardCashTextField;
	private JCheckBox goldClientCheckBox;
	
	public NewClientView() {
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		getContentPane().setLayout(null);
		
		// New client label
		JLabel newClientLabel = new JLabel("New Client");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		newClientLabel.setBounds(137, 11, 126, 25);
		getContentPane().add(newClientLabel);
		
		// Client ID
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forIdLabel.setBounds(49, 64, 46, 14);
		getContentPane().add(forIdLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(49, 89, 114, 20);
		getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forNameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forNameLabel.setBounds(49, 135, 46, 14);
		getContentPane().add(forNameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(49, 160, 114, 20);
		getContentPane().add(nameTextField);
		
		// Client card
		JLabel forSubscriberCardCashLabel = new JLabel("Subscriber card initial cash");
		forSubscriberCardCashLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forSubscriberCardCashLabel.setBounds(216, 64, 178, 14);
		getContentPane().add(forSubscriberCardCashLabel);
		
		subscriberCardCashTextField = new JTextField();
		subscriberCardCashTextField.setColumns(10);
		subscriberCardCashTextField.setBounds(216, 89, 97, 20);
		getContentPane().add(subscriberCardCashTextField);
		
		// Gold client
		JLabel forGoldClientLabel = new JLabel("Set as Gold Client");
		forGoldClientLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forGoldClientLabel.setBounds(216, 135, 114, 14);
		getContentPane().add(forGoldClientLabel);
		
		goldClientCheckBox = new JCheckBox("Gold Client");
		goldClientCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 11));
		goldClientCheckBox.setBounds(216, 159, 97, 23);
		getContentPane().add(goldClientCheckBox);
		
		// Subscribe button
		JButton subscribeClientButton = new JButton("Subscribe client");
		subscribeClientButton.addActionListener((e) -> {
			SubscribeClientController clientSubscriber = new SubscribeClientController();
			try {
				clientSubscriber.subscribeClient(idTextField.getText(),
					nameTextField.getText(),
					subscriberCardCashTextField.getText(),
					goldClientCheckBox.isSelected());
				Alert.showError(this, "Client Subscribed successfully!");
				clearData();
			} catch(CashFormatException | TextNotValidException err) {
				Alert.showError(this, err.getMessage());
			}
		});
		subscribeClientButton.setBounds(65, 210, 133, 23);
		getContentPane().add(subscribeClientButton);
		
		// Close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener((e) -> this.dispose());
		closeButton.setBounds(208, 210, 133, 23);
		getContentPane().add(closeButton);
		
		renderer.render();
	}

	private void clearData() {
		idTextField.setText("");
		nameTextField.setText("");
		subscriberCardCashTextField.setText("");
		goldClientCheckBox.setSelected(false);
	}
	
}
